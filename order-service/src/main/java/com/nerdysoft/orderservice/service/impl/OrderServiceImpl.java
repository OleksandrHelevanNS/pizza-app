package com.nerdysoft.orderservice.service.impl;

import com.nerdysoft.orderservice.dto.CreateOrderRequest;
import com.nerdysoft.orderservice.dto.OrderResponse;
import com.nerdysoft.orderservice.mapper.IngredientMapper;
import com.nerdysoft.orderservice.mapper.OrderMapper;
import com.nerdysoft.orderservice.model.Ingredient;
import com.nerdysoft.orderservice.model.Order;
import com.nerdysoft.orderservice.model.OrderStaus;
import com.nerdysoft.orderservice.repo.OrderRepository;
import com.nerdysoft.orderservice.service.IngredientService;
import com.nerdysoft.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final IngredientService ingredientService;
    private final IngredientMapper ingredientMapper;

    @Override
    public OrderResponse createOrder(CreateOrderRequest request) {
        Order order = orderMapper.toEntity(request);
        Set<Ingredient> ingredients =
                ingredientService.getIngredientsEntities(request.getIngredients());
        order.setIngredients(ingredients);
        order.setOrderStaus(OrderStaus.PENDING);
        Order savedOrder = orderRepository.save(order);

        return OrderResponse.builder()
                .id(savedOrder.getId())
                .pizzaName(savedOrder.getPizzaName())
                .totalPrice(savedOrder.getTotalPrice())
                .pizzaSize(savedOrder.getPizzaSize())
                .amount(savedOrder.getAmount())
                .ingredients(savedOrder
                        .getIngredients()
                        .stream()
                        .map(ingredientMapper::toDto)
                        .collect(Collectors.toSet()))
                .needDelivery(savedOrder.getNeedDelivery())
                .orderStaus(savedOrder.getOrderStaus())
                .phoneNumber(order.getPhoneNumber())
                .build();
    }

    public OrderResponse getOrderById(UUID id) {
        return orderMapper
                .toDto(orderRepository
                        .findById(id)
                        .orElseThrow(() -> new RuntimeException("Order not found")));
    }

}
