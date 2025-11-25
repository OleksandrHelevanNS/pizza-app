package com.nerdysoft.menuservice.service;

import com.nerdysoft.menuservice.dto.CreatePizzaRequest;
import com.nerdysoft.menuservice.dto.PizzaResponse;
import com.nerdysoft.menuservice.dto.UpdatePizzaRequest;
import com.nerdysoft.menuservice.model.PizzaType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PizzaService {
    Mono<PizzaResponse> createPizza(CreatePizzaRequest createPizzaRequest);
    Flux<PizzaResponse> getAllByType(PizzaType type);
    Mono<PizzaResponse> getByKey(PizzaType type,String name);
    Mono<PizzaResponse> updatePizza(PizzaType type, String name, UpdatePizzaRequest request);
}
