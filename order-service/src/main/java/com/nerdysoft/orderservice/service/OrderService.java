package com.nerdysoft.orderservice.service;

import com.nerdysoft.orderservice.dto.CreateOrderRequest;
import com.nerdysoft.orderservice.dto.OrderResponse;
import com.nerdysoft.orderservice.dto.UpdateOrderRequest;
import com.nerdysoft.orderservice.service.strategy.OrderStatus;

import java.util.Set;
import java.util.UUID;

public interface OrderService {
    OrderResponse createOrder(CreateOrderRequest request);
    OrderResponse getOrderById(UUID id);
    OrderResponse updateOrderById(UUID id, UpdateOrderRequest request);
    void deleteOrder(UUID id);
    OrderResponse updateOrderStatus(UUID id, OrderStatus status);
    OrderResponse updateOrderIngredients(UUID id, Set<UUID> ingredients);
}
