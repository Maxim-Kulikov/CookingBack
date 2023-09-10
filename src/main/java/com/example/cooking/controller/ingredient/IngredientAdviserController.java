package com.example.cooking.controller.ingredient;

import com.example.cooking.exception.Violation;
import com.example.cooking.exception.ingredient.IsExistedException;
import com.example.cooking.exception.ingredient.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestControllerAdvice
public class IngredientAdviserController {
    @ExceptionHandler(IsExistedException.class)
    public ResponseEntity<List<Violation>> handleIsExistedException(IsExistedException e) {
        List<Violation> violations = List.of(
                new Violation("idOfExistedEntity", String.valueOf(e.getId())),
                new Violation("message", e.getMessage())
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(violations);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<List<Violation>> handleNotFoundException(NotFoundException e) {
        List<Violation> violations = List.of(
                new Violation("message", e.getMessage())
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(violations);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<Violation>> handleConstraintViolationException(ConstraintViolationException e) {
        List<Violation> violations = e.getConstraintViolations().stream()
                .map(violation -> new Violation(violation.getPropertyPath().toString(), violation.getMessage()))
                .toList();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(violations);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Violation>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<Violation> violations = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new Violation(error.getField(), error.getDefaultMessage()))
                .toList();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(violations);
    }




}
