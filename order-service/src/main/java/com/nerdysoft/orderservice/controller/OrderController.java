package com.nerdysoft.orderservice.controller;

import com.nerdysoft.orderservice.dto.CreateOrderRequest;
import com.nerdysoft.orderservice.dto.OrderResponse;
import com.nerdysoft.orderservice.dto.UpdateOrderRequest;
import com.nerdysoft.orderservice.service.OrderService;
import com.nerdysoft.orderservice.strategy.OrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        return new ResponseEntity<>(orderService.createOrder(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable UUID id) {
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@PathVariable UUID id,
                                                     @Valid @RequestBody UpdateOrderRequest request) {
        return new ResponseEntity<>(orderService.updateOrderById(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable UUID id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderResponse> updateOrderStatus(@PathVariable UUID id,
                                                           @RequestParam(value = "status", required = false) OrderStatus status) {
        return new ResponseEntity<>(orderService.updateOrderStatus(id, status), HttpStatus.OK);
    }

    @PatchMapping("/{id}/ingredients")
    public ResponseEntity<OrderResponse> updateOrderIngredients(@PathVariable UUID id,
                                                                @NotEmpty @RequestBody Set<UUID> ingredients) {
        return new ResponseEntity<>(orderService.updateOrderIngredients(id, ingredients), HttpStatus.OK);
    }
}
