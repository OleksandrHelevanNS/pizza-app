package com.nerdysoft.orderservice.mapper;

import com.nerdysoft.orderservice.config.MapperConfig;
import com.nerdysoft.orderservice.dto.CreateIngredientRequest;
import com.nerdysoft.orderservice.dto.IngredientResponse;
import com.nerdysoft.orderservice.model.Ingredient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = MapperConfig.class)
public interface IngredientMapper {
    IngredientResponse toDto(Ingredient ingredient);
    Ingredient toEntity(CreateIngredientRequest request);
}
