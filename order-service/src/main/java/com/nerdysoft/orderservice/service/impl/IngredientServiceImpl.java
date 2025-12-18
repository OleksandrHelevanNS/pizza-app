package com.nerdysoft.orderservice.service.impl;

import com.nerdysoft.orderservice.dto.CreateIngredientRequest;
import com.nerdysoft.orderservice.dto.CreateOrderRequest;
import com.nerdysoft.orderservice.dto.IngredientResponse;
import com.nerdysoft.orderservice.mapper.IngredientMapper;
import com.nerdysoft.orderservice.model.Ingredient;
import com.nerdysoft.orderservice.repo.IngredientRepository;
import com.nerdysoft.orderservice.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    @Override
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

    public IngredientResponse createIngredient(CreateIngredientRequest request) {
        return ingredientMapper
                .toDto(ingredientRepository
                        .save(ingredientMapper
                                .toEntity(request)));
    }

    public Page<IngredientResponse> getIngredients(Pageable pageable) {
        Page<Ingredient> ingredients = ingredientRepository.findAll(pageable);
        return ingredients.map(ingredientMapper::toDto);
    }
}
