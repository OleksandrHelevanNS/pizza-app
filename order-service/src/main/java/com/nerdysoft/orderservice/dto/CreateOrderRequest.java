package com.nerdysoft.orderservice.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class CreateOrderRequest {

    @NotBlank(message = "Pizza name must not be blank")
    private String pizzaName;

    @NotNull(message = "Total price must not be null")
    @Positive(message = "Total price must be greater than zero")
    private BigDecimal totalPrice;

    @NotNull(message = "Pizza size must not be null")
    @Positive(message = "Pizza size must be greater than zero")
    private Integer pizzaSize;

    @NotNull(message = "Amount must not be null")
    @Positive(message = "Amount must be greater than zero")
    private Integer amount;

    @NotEmpty(message = "Ingredients must not be empty")
    private Set<UUID> ingredients;

    @NotNull(message = "Delivery flag must not be null")
    private Boolean needDelivery;

    @NotBlank(message = "Phone number must not be blank")
    @Pattern(
            regexp = "^\\+?[0-9]{10,15}$",
            message = "Phone number must be valid"
    )
    private String phoneNumber;
}
