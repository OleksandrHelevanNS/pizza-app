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
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pizzas")
@RequiredArgsConstructor
public class PizzaController {
    private final PizzaService pizzaService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = "multipart/form-data")
    public Mono<ResponseEntity<PizzaResponse>> createPizzaWithImage(
            @RequestPart("pizza") Mono<CreatePizzaRequest> pizzaRequestMono,
            @RequestPart("image") FilePart imageFile
    ) {
        return pizzaRequestMono
                .flatMap(pizzaRequest -> pizzaService.createPizza(pizzaRequest, imageFile))
                .map(savedPizza -> ResponseEntity.status(HttpStatus.CREATED).body(savedPizza));
    }

    @GetMapping
    public Flux<PizzaResponse> getPizzas(@RequestParam PizzaType type) {
        return pizzaService.getAllByType(type);
    }

    @GetMapping("/one")
    public Mono<ResponseEntity<PizzaResponse>> getPizza(@RequestParam PizzaType type,
                                                        @RequestParam String name) {
        return pizzaService.getByKey(type, name)
                .map(ResponseEntity::ok);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public Mono<ResponseEntity<PizzaResponse>> updatePizza(
            @RequestParam PizzaType type,
            @RequestParam String name,
            @RequestBody @Valid UpdatePizzaRequest request
    ) {
        return pizzaService.updatePizza(type, name, request)
                .map(ResponseEntity::ok);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping
    public Mono<ResponseEntity<String>> deletePizza(
            @RequestParam PizzaType type,
            @RequestParam String name
    ) {
        return pizzaService.deletePizza(type, name)
                .map(s -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(s));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/image")
    public Mono<ResponseEntity<String>> uploadPizzaImage(
            @RequestParam PizzaType type,
            @RequestParam String name,
            @RequestPart("image") FilePart imageFile
    ) {
        return pizzaService.uploadImage(type, name, imageFile)
                .map(ResponseEntity::ok);
    }

}
