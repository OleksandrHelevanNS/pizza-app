package com.nerdysoft.orderservice.mapper;

import com.nerdysoft.orderservice.config.MapperConfig;
import com.nerdysoft.orderservice.dto.CreateOrderRequest;
import com.nerdysoft.orderservice.dto.OrderResponse;
import com.nerdysoft.orderservice.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", config = MapperConfig.class)
public interface OrderMapper {

    @Mapping(target = "ingredients", ignore = true)
    Order toEntity(CreateOrderRequest request);

    @Mapping(target = "ingredients", source = "ingredients")
    OrderResponse toDto(Order order);
}
