package com.nerdysoft.orderservice.service;

import com.nerdysoft.orderservice.dto.CreateOrderRequest;
import com.nerdysoft.orderservice.dto.OrderResponse;

public interface OrderService {
    OrderResponse createOrder(CreateOrderRequest request);
}
