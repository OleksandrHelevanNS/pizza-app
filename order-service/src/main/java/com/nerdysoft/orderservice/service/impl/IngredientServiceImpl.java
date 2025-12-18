package com.nerdysoft.orderservice.service.impl;

import com.nerdysoft.orderservice.dto.CreateIngredientRequest;
import com.nerdysoft.orderservice.dto.IngredientResponse;
import com.nerdysoft.orderservice.dto.UpdateIngredientRequest;
import com.nerdysoft.orderservice.exception.IngredientAlreadyExistsException;
import com.nerdysoft.orderservice.exception.IngredientNotFoundException;
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
                            new IngredientNotFoundException("Ingredient not found with id " + id)
                    );

            ingredients.add(ingredient);
        }

        return ingredients;
    }

    @Override
    public IngredientResponse createIngredient(CreateIngredientRequest request) {
        if (ingredientRepository.existsByName(request.getName()))
            throw new IngredientAlreadyExistsException("Name already exists: " + request.getName());

        return ingredientMapper
                .toDto(ingredientRepository
                        .save(ingredientMapper
                                .toEntity(request)));
    }

    @Override
    public Page<IngredientResponse> getIngredients(Pageable pageable) {
        Page<Ingredient> ingredients = ingredientRepository.findAll(pageable);
        return ingredients.map(ingredientMapper::toDto);
    }

    @Override
    public IngredientResponse updateIngredient(UUID id, UpdateIngredientRequest request) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException("Ingredient not found: " + id));

        if (request.getName() != null && !request.getName().equals(ingredient.getName())) {
            if (ingredientRepository.existsByName(request.getName()))
                throw new IngredientAlreadyExistsException("Name already exists: " + request.getName());
            ingredient.setName(request.getName());
        }

        if (request.getPrice() != null) {
            ingredient.setPrice(request.getPrice());
        }

        if (request.getPortion() != null) {
            ingredient.setPortion(request.getPortion());
        }

        ingredientRepository.save(ingredient);

        return ingredientMapper.toDto(ingredient);
    }

    @Override
    public void deleteIngredient(UUID id) {
        if (!ingredientRepository.existsById(id)) {
            throw new IngredientNotFoundException("Ingredient not found: " + id);
        }
        ingredientRepository.deleteById(id);
    }

}
