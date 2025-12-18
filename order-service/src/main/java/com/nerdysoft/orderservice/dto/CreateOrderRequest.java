package com.nerdysoft.orderservice.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class CreateOrderRequest {
    private String pizzaName;
    private BigDecimal totalPrice;
    private Integer pizzaSize;
    private Integer amount;
    private Set<UUID> ingredients;
    private Boolean needDelivery;
    private String phoneNumber;
}
