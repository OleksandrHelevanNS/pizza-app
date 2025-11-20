package com.nerdysoft.menuservice.controller;

import com.nerdysoft.menuservice.dto.CreatePizzaRequest;
import com.nerdysoft.menuservice.dto.PizzaResponse;
import com.nerdysoft.menuservice.service.PizzaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pizzas")
@RequiredArgsConstructor
public class PizzaController {
    private final PizzaService pizzaService;

    @PostMapping
    public Mono<ResponseEntity<PizzaResponse>> createPizza(@RequestBody CreatePizzaRequest pizza) {
        return pizzaService.createPizza(pizza)
                .map(savedPizza -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(savedPizza));
    }

    @GetMapping()
    public Mono<Page<PizzaResponse>> getPizzas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return pizzaService.getPizzas(pageable);
    }


}
