package com.nerdysoft.menuservice.dto;

import jakarta.validation.constraints.DecimalMax;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class UpdatePizzaRequest {
    private BigDecimal price;

    private Set<Integer> sizes;

    private Set<String> ingredients;

    @DecimalMax("10")
    private BigDecimal rating;

    private String image;
}
