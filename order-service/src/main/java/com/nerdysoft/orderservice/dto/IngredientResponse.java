package com.nerdysoft.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class IngredientResponse {
    private UUID id;
    private String name;
    private BigDecimal price;
    private Integer portion;
}
