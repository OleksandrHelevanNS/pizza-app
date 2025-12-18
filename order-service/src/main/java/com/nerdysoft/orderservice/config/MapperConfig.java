package com.nerdysoft.orderservice.config;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;

@org.mapstruct.MapperConfig(
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.SETTER,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface MapperConfig {

}