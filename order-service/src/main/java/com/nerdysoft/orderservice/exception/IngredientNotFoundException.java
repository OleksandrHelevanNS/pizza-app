package com.nerdysoft.orderservice.exception;

import com.nerdysoft.common.exception.CafeAppException;

public class IngredientNotFoundException extends CafeAppException {
    public IngredientNotFoundException(String message) {
        super(message);
    }
}
