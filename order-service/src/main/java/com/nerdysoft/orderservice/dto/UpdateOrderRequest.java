package com.nerdysoft.orderservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class UpdateOrderRequest {

    @NotNull(message = "Pizza name must not be null")
    private String pizzaName;

    @NotNull(message = "Total price must not be null")
    @Positive(message = "Pizza price must be greater than zero")
    private BigDecimal totalPrice;

    @NotNull(message = "Pizza size must not be null")
    @Positive(message = "Pizza size must be greater than zero")
    private Integer pizzaSize;

    @NotNull(message = "Amount must not be null")
    @Positive(message = "Pizza amount must be greater than zero")
    private Integer amount;

    @NotNull(message = "Delivery flag must not be null")
    private Boolean needDelivery;

    @NotNull(message = "Phone number must not be null")
    private String phoneNumber;
}
