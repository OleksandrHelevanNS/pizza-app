package com.nerdysoft.orderservice.service.impl;

import com.nerdysoft.orderservice.dto.IngredientResponse;
import com.nerdysoft.orderservice.mapper.IngredientMapper;
import com.nerdysoft.orderservice.model.Ingredient;
import com.nerdysoft.orderservice.repo.IngredientRepository;
import com.nerdysoft.orderservice.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    public Set<Ingredient> getIngredientsEntities(Set<UUID> ingredientsId) {

        Set<Ingredient> ingredients = new HashSet<>();

        for (UUID id : ingredientsId) {
            Ingredient ingredient = ingredientRepository.findById(id)
                    .orElseThrow(() ->
                            new IllegalArgumentException("Ingredient not found: " + id)
                    );

            ingredients.add(ingredient);
        }

        return ingredients;
    }
}
