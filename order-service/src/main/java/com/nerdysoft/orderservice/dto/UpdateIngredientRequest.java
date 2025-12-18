package com.nerdysoft.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class UpdateIngredientRequest {
    private String name;
    private BigDecimal price;
    private Integer portion;
}
