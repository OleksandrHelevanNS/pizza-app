package com.nerdysoft.menuservice.factory;

import com.nerdysoft.menuservice.dto.CreatePizzaRequest;
import com.nerdysoft.menuservice.model.Pizza;
import com.nerdysoft.menuservice.model.PizzaKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PizzaFactory {

    private final PizzaKeyFactory pizzaKeyFactory;

    public Pizza create(CreatePizzaRequest request) {
        PizzaKey key = pizzaKeyFactory.create(request.getType(), request.getName());

        return Pizza.builder()
                .key(key)
                .price(request.getPrice())
                .sizes(request.getSizes())
                .ingredients(request.getIngredients())
                .rating(request.getRating())
                .image(request.getImage())
                .build();
    }
}
