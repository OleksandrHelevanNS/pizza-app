package com.nerdysoft.menuservice.service.impl;

import com.nerdysoft.menuservice.dto.CreatePizzaRequest;
import com.nerdysoft.menuservice.dto.PizzaResponse;
import com.nerdysoft.menuservice.factory.PizzaFactory;
import com.nerdysoft.menuservice.mapper.PizzaMapper;
import com.nerdysoft.menuservice.model.Pizza;
import com.nerdysoft.menuservice.repo.PizzaRepository;
import com.nerdysoft.menuservice.service.PizzaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class PizzaServiceImpl implements PizzaService {
    private final PizzaRepository pizzaRepository;
    private final PizzaFactory pizzaFactory;
    private final PizzaMapper pizzaMapper;

    @Transactional
    @Override
    public Mono<PizzaResponse> createPizza(CreatePizzaRequest createPizzaRequest) {
        Pizza pizza = pizzaFactory.create(createPizzaRequest);
        return pizzaRepository.save(pizza).map(pizzaMapper::toDto);
    }

    @Override
    public Mono<Page<PizzaResponse>> getPizzas(Pageable pageable) {
        return pizzaRepository.findAllBy(pageable)
                .map(pizzaMapper::toDto)
                .collectList()
                .zipWith(pizzaRepository.count())
                .map(tuple -> new PageImpl<>(tuple.getT1(), pageable, tuple.getT2()));
    }

}
