package com.nerdysoft.menuservice.factory;

import com.nerdysoft.menuservice.dto.CreatePizzaRequest;
import com.nerdysoft.menuservice.model.PizzaKey;
import org.springframework.stereotype.Component;

@Component
public class PizzaKeyFactory {

    public PizzaKey create(CreatePizzaRequest pizza) {
        return PizzaKey.builder()
                .type(pizza.getType())
                .name(pizza.getName())
                .build();
    }
}

