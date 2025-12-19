package com.nerdysoft.orderservice.exception;

import com.nerdysoft.common.exception.CafeAppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IngredientAlreadyExistsException.class)
    public ResponseEntity<String> handleException(IngredientAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(IngredientNotFoundException.class)
    public ResponseEntity<String> handleException(IngredientNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> handleException(OrderNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ChangeOrderException.class)
    public ResponseEntity<String> handleException(ChangeOrderException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(CafeAppException.class)
    public ResponseEntity<String> handleCafeAppException(CafeAppException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }


}
