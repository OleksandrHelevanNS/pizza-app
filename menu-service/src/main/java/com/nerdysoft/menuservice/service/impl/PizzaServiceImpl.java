package com.nerdysoft.menuservice.service.impl;

import com.nerdysoft.menuservice.model.Pizza;
import com.nerdysoft.menuservice.repo.PizzaRepository;
import com.nerdysoft.menuservice.service.PizzaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PizzaServiceImpl implements PizzaService {
    private final PizzaRepository pizzaRepository;

    public Mono<Pizza> createPizza(String name, BigDecimal price) {
        Pizza pizza = Pizza.builder()
                .id(UUID.randomUUID())
                .name(name)
                .price(price)
                .build();

        return pizzaRepository.save(pizza);
    }

}
