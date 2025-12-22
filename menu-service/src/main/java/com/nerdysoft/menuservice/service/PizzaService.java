package com.nerdysoft.menuservice.service;

import com.nerdysoft.menuservice.dto.CreatePizzaRequest;
import com.nerdysoft.menuservice.dto.PizzaResponse;
import com.nerdysoft.menuservice.dto.UpdatePizzaRequest;
import com.nerdysoft.menuservice.model.PizzaType;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PizzaService {
    Mono<PizzaResponse> createPizza(CreatePizzaRequest createPizzaRequest, FilePart imageFile);
    Flux<PizzaResponse> getAllByType(PizzaType type);
    Mono<PizzaResponse> getByKey(PizzaType type,String name);
    Mono<PizzaResponse> updatePizza(PizzaType type, String name, UpdatePizzaRequest request);
    Mono<String> deletePizza(PizzaType type, String name);
    Mono<String> uploadImage(PizzaType type, String name, FilePart imageFile);
}
