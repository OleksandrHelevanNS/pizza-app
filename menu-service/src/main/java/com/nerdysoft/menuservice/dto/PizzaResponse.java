package com.nerdysoft.menuservice.dto;

import com.nerdysoft.menuservice.model.PizzaType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
public class PizzaResponse {
    private String name;
    private PizzaType type;
    private BigDecimal price;
    private Set<Integer> sizes;
    private Set<String> ingredients;
    private BigDecimal rating;
    private String image;
}
