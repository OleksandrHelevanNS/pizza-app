package com.nerdysoft.menuservice.service;

import com.nerdysoft.menuservice.dto.CreatePizzaRequest;
import com.nerdysoft.menuservice.dto.PizzaResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Mono;

public interface PizzaService {
    Mono<PizzaResponse> createPizza(CreatePizzaRequest createPizzaRequest);
    Mono<Page<PizzaResponse>> getPizzas(Pageable pageable);
}
