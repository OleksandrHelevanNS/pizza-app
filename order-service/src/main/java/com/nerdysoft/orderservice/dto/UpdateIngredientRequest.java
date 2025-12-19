package com.nerdysoft.orderservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class UpdateIngredientRequest {

    @NotBlank(message = "Ingredient name must not be blank")
    private String name;

    @NotNull(message = "Ingredient price must not be null")
    @Positive(message = "Ingredient price must be greater than zero")
    private BigDecimal price;

    @NotNull(message = "Ingredient portion must not be null")
    @Positive(message = "Ingredient portion must be greater than zero")
    private Integer portion;
}
