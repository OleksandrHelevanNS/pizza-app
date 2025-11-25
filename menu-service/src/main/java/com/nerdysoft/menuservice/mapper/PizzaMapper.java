package com.nerdysoft.menuservice.mapper;

import com.nerdysoft.menuservice.config.MapperConfig;
import com.nerdysoft.menuservice.dto.PizzaResponse;
import com.nerdysoft.menuservice.dto.UpdatePizzaRequest;
import com.nerdysoft.menuservice.model.Pizza;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", config = MapperConfig.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PizzaMapper {

    @Mapping(target = "name", source = "key.name")
    @Mapping(target = "type", source = "key.type")
    PizzaResponse toDto(Pizza pizza);

    void updatePizzaFromDto(UpdatePizzaRequest dto, @MappingTarget Pizza entity);
}
