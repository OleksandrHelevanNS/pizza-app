package com.nerdysoft.orderservice.controller;

import com.nerdysoft.orderservice.dto.CreateIngredientRequest;
import com.nerdysoft.orderservice.dto.IngredientResponse;
import com.nerdysoft.orderservice.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @PostMapping
    public ResponseEntity<IngredientResponse> createIngredient(@RequestBody CreateIngredientRequest request) {
        return new ResponseEntity<>(ingredientService.createIngredient(request), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<IngredientResponse>> getAllIngredients(@PageableDefault Pageable pageable) {
        return new ResponseEntity<>(ingredientService.getIngredients(pageable), HttpStatus.OK);

    }
}