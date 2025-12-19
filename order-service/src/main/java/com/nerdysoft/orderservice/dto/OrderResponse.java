package com.nerdysoft.orderservice.dto;

import com.nerdysoft.orderservice.strategy.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
public class OrderResponse {
    private UUID id;
    private String pizzaName;
    private BigDecimal totalPrice;
    private Integer pizzaSize;
    private Integer amount;
    private Set<IngredientResponse> ingredients;
    private Boolean needDelivery;
    private OrderStatus orderStaus;
    private String phoneNumber;
}
