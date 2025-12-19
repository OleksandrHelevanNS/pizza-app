package com.nerdysoft.orderservice.controller;

import com.nerdysoft.orderservice.dto.CreateIngredientRequest;
import com.nerdysoft.orderservice.dto.IngredientResponse;
import com.nerdysoft.orderservice.dto.UpdateIngredientRequest;
import com.nerdysoft.orderservice.service.IngredientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @PostMapping
    public ResponseEntity<IngredientResponse> createIngredient(@Valid @RequestBody CreateIngredientRequest request) {
        return new ResponseEntity<>(ingredientService.createIngredient(request), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<IngredientResponse>> getAllIngredients(@PageableDefault Pageable pageable) {
        return new ResponseEntity<>(ingredientService.getIngredients(pageable), HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredientResponse> updateIngredient(@Valid @RequestBody UpdateIngredientRequest request,
                                                               @PathVariable UUID id) {
        return new ResponseEntity<>(ingredientService.updateIngredient(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable UUID id) {
        ingredientService.deleteIngredient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}