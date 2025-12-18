package com.nerdysoft.orderservice.exception;

import com.nerdysoft.common.exception.CafeAppException;

public class IngredientAlreadyExistsException extends CafeAppException {
    public IngredientAlreadyExistsException(String message) {
        super(message);
    }
}
