package com.nerdysoft.menuservice.service.impl;

import com.nerdysoft.menuservice.dto.CreatePizzaRequest;
import com.nerdysoft.menuservice.dto.PizzaResponse;
import com.nerdysoft.menuservice.dto.UpdatePizzaRequest;
import com.nerdysoft.menuservice.exception.ItemAlreadyExistsException;
import com.nerdysoft.menuservice.exception.ItemNotFoundException;
import com.nerdysoft.menuservice.factory.PizzaFactory;
import com.nerdysoft.menuservice.mapper.PizzaMapper;
import com.nerdysoft.menuservice.model.Pizza;
import com.nerdysoft.menuservice.model.PizzaType;
import com.nerdysoft.menuservice.repo.PizzaRepository;
import com.nerdysoft.menuservice.service.ImageStorageService;
import com.nerdysoft.menuservice.service.PizzaService;
import com.nerdysoft.menuservice.util.SuccessMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.nerdysoft.menuservice.util.ErrorMessage;


@Service
@RequiredArgsConstructor
public class PizzaServiceImpl implements PizzaService {
    private final PizzaRepository pizzaRepository;
    private final PizzaFactory pizzaFactory;
    private final PizzaMapper pizzaMapper;
    private final ImageStorageService imageStorageService;

    @Override
    public Mono<PizzaResponse> createPizza(CreatePizzaRequest createPizzaRequest, FilePart imageFile) {
        return pizzaRepository
                .existsByKeyTypeAndKeyName(createPizzaRequest.getType(), createPizzaRequest.getName())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new ItemAlreadyExistsException(ErrorMessage.PIZZA_ALREADY_EXISTS));
                    }

                    return imageStorageService.upload(imageFile)
                            .flatMap(url -> {
                                createPizzaRequest.setImage(url);
                                Pizza pizza = pizzaFactory.create(createPizzaRequest);
                                return pizzaRepository.save(pizza)
                                        .map(pizzaMapper::toDto);
                            });
                });
    }

    @Override
    public Flux<PizzaResponse> getAllByType(PizzaType type) {
        return pizzaRepository.findAllByKeyType(type)
                .map(pizzaMapper::toDto);
    }

    @Override
    public Mono<PizzaResponse> getByKey(PizzaType type, String name) {
        return pizzaRepository.findByKeyTypeAndKeyName(type, name)
                .map(pizzaMapper::toDto)
                .switchIfEmpty(Mono.error(new ItemNotFoundException(ErrorMessage.PIZZA_NOT_FOUND)));
    }

    @Override
    public Mono<PizzaResponse> updatePizza(PizzaType type, String name, UpdatePizzaRequest request) {
        return pizzaRepository.findByKeyTypeAndKeyName(type, name)
                .switchIfEmpty(Mono.error(new ItemNotFoundException(ErrorMessage.PIZZA_NOT_FOUND)))
                .map(pizza -> {
                    pizzaMapper.updatePizzaFromDto(request, pizza);
                    return pizza;
                })
                .flatMap(pizzaRepository::save)
                .map(pizzaMapper::toDto);
    }

    @Override
    public Mono<String> deletePizza(PizzaType type, String name) {
        return pizzaRepository.findByKeyTypeAndKeyName(type, name)
                .switchIfEmpty(Mono.error(new ItemNotFoundException(ErrorMessage.PIZZA_NOT_FOUND)))
                .flatMap(pizza ->
                        pizzaRepository.delete(pizza)
                                .then(Mono.just(SuccessMessage.PIZZA_DELETED_SUCCESSFULLY))
                );
    }

    @Override
    public Mono<String> uploadImage(PizzaType type, String name, FilePart imageFile) {
        return pizzaRepository.findByKeyTypeAndKeyName(type, name)
                .switchIfEmpty(Mono.error(new ItemNotFoundException(ErrorMessage.PIZZA_NOT_FOUND)))
                .flatMap(pizza ->
                        imageStorageService.upload(imageFile)
                                .flatMap(url -> {
                                    pizza.setImage(url);
                                    return pizzaRepository.save(pizza)
                                            .then(Mono.just(url));
                                })
                );
    }


}
