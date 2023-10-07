package com.example.cooking.controller;

import com.example.cooking.dto.response.Error;
import com.example.cooking.exception.IsExistedException;
import com.example.cooking.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class AdviserController {
    @ExceptionHandler(IsExistedException.class)
    public ResponseEntity<Error> handleIsExistedException(IsExistedException e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Error.builder()
                        .message(e.getMessage())
                        .code(HttpStatus.CONFLICT.value())
                        .build());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Error> handleNotFoundException(NotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Error.builder()
                        .message(e.getMessage())
                        .code(HttpStatus.NOT_FOUND.value())
                        .build());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Error> handleConstraintViolationException(ConstraintViolationException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Error.builder()
                        .message(e.getMessage())
                        .code(HttpStatus.BAD_REQUEST.value())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Error.builder()
                        .message(e.getMessage())
                        .code(HttpStatus.BAD_REQUEST.value())
                        .build());
    }


}
