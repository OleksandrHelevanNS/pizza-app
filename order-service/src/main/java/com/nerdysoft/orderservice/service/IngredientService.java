package com.nerdysoft.orderservice.service;

import com.nerdysoft.orderservice.model.Ingredient;

import java.util.Set;
import java.util.UUID;

public interface IngredientService {
    Set<Ingredient> getIngredientsEntities(Set<UUID> ingredientsId);
}
