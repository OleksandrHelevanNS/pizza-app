package com.nerdysoft.menuservice.exception;

import com.nerdysoft.common.exception.CafeAppException;

public class ItemNotFoundException extends CafeAppException {
    public ItemNotFoundException(String message) {
        super(message);
    }
}
