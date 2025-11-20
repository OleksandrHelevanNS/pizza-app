package com.nerdysoft.menuservice.mapper;

import com.nerdysoft.menuservice.config.MapperConfig;
import com.nerdysoft.menuservice.dto.PizzaResponse;
import com.nerdysoft.menuservice.model.Pizza;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface PizzaMapper {

    @Mapping(target = "name", source = "key.name")
    @Mapping(target = "type", source = "key.type")
    PizzaResponse toDto(Pizza pizza);
}
