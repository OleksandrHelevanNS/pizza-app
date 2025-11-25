package com.nerdysoft.menuservice.controller;

import com.nerdysoft.menuservice.dto.CreatePizzaRequest;
import com.nerdysoft.menuservice.dto.PizzaResponse;
import com.nerdysoft.menuservice.dto.UpdatePizzaRequest;
import com.nerdysoft.menuservice.model.PizzaType;
import com.nerdysoft.menuservice.service.PizzaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
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
    public Flux<PizzaResponse> getPizzas(@RequestParam PizzaType type) {
        return pizzaService.getAllByType(type);
    }

    @GetMapping("/one")
    public Mono<ResponseEntity<PizzaResponse>> getPizza(@RequestParam PizzaType type,
                                                        @RequestParam String name) {
        return pizzaService.getByKey(type, name)
                .map(ResponseEntity::ok);
    }

    @PutMapping()
    public Mono<ResponseEntity<PizzaResponse>> updatePizza(
            @RequestParam PizzaType type,
            @RequestParam String name,
            @RequestBody @Valid UpdatePizzaRequest request
    ) {
        return pizzaService.updatePizza(type, name, request)
                .map(ResponseEntity::ok);
    }
}
