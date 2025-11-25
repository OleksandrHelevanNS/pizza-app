package com.nerdysoft.menuservice.factory;

import com.nerdysoft.menuservice.dto.CreatePizzaRequest;
import com.nerdysoft.menuservice.model.PizzaKey;
import com.nerdysoft.menuservice.model.PizzaType;
import org.springframework.stereotype.Component;

@Component
public class PizzaKeyFactory {

    public PizzaKey create(PizzaType type, String name) {
        return PizzaKey.builder()
                .type(type)
                .name(name)
                .build();
    }
}

