package com.nerdysoft.menuservice.dto;

import com.nerdysoft.menuservice.model.PizzaType;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class CreatePizzaRequest {
    @NotNull
    private String name;
    @NotNull
    private PizzaType type;

    private BigDecimal price;

    private Set<Integer> sizes;

    private Set<String> ingredients;

    @DecimalMax("10")
    private BigDecimal rating;

    private String image;
}
