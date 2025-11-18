package com.nerdysoft.menuservice.controller;

import com.nerdysoft.menuservice.model.Pizza;
import com.nerdysoft.menuservice.service.PizzaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pizzas")
@RequiredArgsConstructor
public class PizzaController {
    private final PizzaService pizzaService;

    @PostMapping
    public Mono<ResponseEntity<Pizza>> createPizza(@RequestBody Pizza pizza) {
        return pizzaService.createPizza(pizza.getName(), pizza.getPrice())
                .map(savedPizza -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(savedPizza));
    }

}
