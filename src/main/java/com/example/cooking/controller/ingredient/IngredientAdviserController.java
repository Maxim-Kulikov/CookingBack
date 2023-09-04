package com.example.cooking.controller.ingredient;

import com.example.cooking.exception.ingredient.IsExistedException;
import com.example.cooking.exception.ingredient.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class IngredientAdviserController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(IsExistedException.class)
    public ResponseEntity<String> handleIsExistedException(IsExistedException e) {
        String message = e.getMessage();
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .header("id", String.valueOf(e.getId()))
                .header("message", message)
                .body(e.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException e) {
        String message = e.getMessage();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .header("message", message)
                .body(e.getMessage());
    }

}
