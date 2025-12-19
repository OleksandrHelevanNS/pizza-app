package com.nerdysoft.menuservice.exception;

import com.nerdysoft.common.exception.CafeAppException;

public class ItemAlreadyExistsException extends CafeAppException {
    public ItemAlreadyExistsException(String message) {
        super(message);
    }
}
