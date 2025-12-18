package com.nerdysoft.orderservice.dto;

import com.nerdysoft.orderservice.model.OrderStaus;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {
    private String pizzaName;
    private BigDecimal totalPrice;
    private Integer pizzaSize;
    private Integer amount;
    private Set<UUID> ingredients;
    private Boolean needDelivery;
    private OrderStaus orderStaus;
    private String phoneNumber;
}
