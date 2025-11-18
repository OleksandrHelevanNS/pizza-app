package com.nerdysoft.menuservice.service;

import com.nerdysoft.menuservice.model.Pizza;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface PizzaService {
    Mono<Pizza> createPizza(String name, BigDecimal price);
}
