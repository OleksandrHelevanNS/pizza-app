package com.nerdysoft.orderservice.dto;

import com.nerdysoft.orderservice.model.Ingredient;
import com.nerdysoft.orderservice.model.OrderStaus;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

public class OrderResponse {
    private UUID id;
    private String pizzaName;
    private BigDecimal totalPrice;
    private Integer pizzaSize;
    private Integer amount;
    private Set<Ingredient> ingredients;
    private Boolean needDelivery;
    private OrderStaus orderStaus;
}
