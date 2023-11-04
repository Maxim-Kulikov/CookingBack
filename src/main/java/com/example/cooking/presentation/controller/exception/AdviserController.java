package com.example.cooking.presentation.controller.exception;

import com.example.cooking.exception.IsExistedException;
import com.example.cooking.exception.NotFoundException;
import com.example.cooking.presentation.dto.Error;
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
                        .code(HttpStatus.CONFLICT.name())
                        .build());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Error> handleNotFoundException(NotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Error.builder()
                        .message(e.getMessage())
                        .code(HttpStatus.NOT_FOUND.name())
                        .build());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Error> handleConstraintViolationException(ConstraintViolationException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Error.builder()
                        .message(e.getMessage())
                        .code(HttpStatus.BAD_REQUEST.name())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Error.builder()
                        .message(e.getMessage())
                        .code(HttpStatus.BAD_REQUEST.name())
                        .build());
    }


}
