package com.nerdysoft.orderservice.service;

import com.nerdysoft.orderservice.dto.CreateIngredientRequest;
import com.nerdysoft.orderservice.dto.IngredientResponse;
import com.nerdysoft.orderservice.model.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;
import java.util.UUID;

public interface IngredientService {
    Set<Ingredient> getIngredientsEntities(Set<UUID> ingredientsId);
    IngredientResponse createIngredient(CreateIngredientRequest request);
    Page<IngredientResponse> getIngredients(Pageable pageable);
}
