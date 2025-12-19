package com.nerdysoft.orderservice.service.impl;

import com.nerdysoft.orderservice.dto.CreateOrderRequest;
import com.nerdysoft.orderservice.dto.OrderResponse;
import com.nerdysoft.orderservice.dto.UpdateOrderRequest;
import com.nerdysoft.orderservice.exception.ChangeOrderException;
import com.nerdysoft.orderservice.exception.OrderNotFoundException;
import com.nerdysoft.orderservice.mapper.IngredientMapper;
import com.nerdysoft.orderservice.mapper.OrderMapper;
import com.nerdysoft.orderservice.model.Ingredient;
import com.nerdysoft.orderservice.model.Order;
import com.nerdysoft.orderservice.service.strategy.OrderStatus;
import com.nerdysoft.orderservice.repo.OrderRepository;
import com.nerdysoft.orderservice.service.IngredientService;
import com.nerdysoft.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public OrderResponse createOrder(CreateOrderRequest request) {
        Order order = orderMapper.toEntity(request);
        Set<Ingredient> ingredients =
                ingredientService.getIngredientsEntities(request.getIngredients());
        order.setIngredients(ingredients);
        order.setOrderStaus(OrderStatus.PENDING);
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

    @Override
    public OrderResponse getOrderById(UUID id) {
        return orderMapper
                .toDto(orderRepository
                        .findById(id)
                        .orElseThrow(() -> new OrderNotFoundException("Order not found")));
    }

    @Override
    @Transactional
    public OrderResponse updateOrderById(UUID id, UpdateOrderRequest request) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found: " + id));

        if (order.getOrderStaus() != OrderStatus.PENDING)
            throw new ChangeOrderException("You can`t change order, its status isn`t pending");

        order.setPizzaName(request.getPizzaName());
        order.setTotalPrice(request.getTotalPrice());
        order.setPizzaSize(request.getPizzaSize());
        order.setAmount(request.getAmount());
        order.setNeedDelivery(request.getNeedDelivery());
        order.setPhoneNumber(request.getPhoneNumber());

        Order saved = orderRepository.save(order);
        return orderMapper.toDto(saved);
    }

    @Override
    @Transactional
    public void deleteOrder(UUID id) {
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException("Order not found: " + id);
        }
        orderRepository.deleteById(id);

    }

    @Override
    @Transactional
    public OrderResponse updateOrderStatus(UUID id, OrderStatus status) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found: " + id));
        if (status == null)
            order.setOrderStaus(order.getOrderStaus().next());
        else
            order.setOrderStaus(status);
        return orderMapper.toDto(orderRepository.save(order));
    }

}
