package com.example.restful.exception;

import com.example.restful.controller.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handler(Exception ex, WebRequest req) {
        ExceptionResponse er = new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false));

        return new ResponseEntity(er, HttpStatus.INTERNAL_SERVER_ERROR);
    };

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> userHandler(Exception ex, WebRequest req) {
        ExceptionResponse er = new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false));

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(er);
    };

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ExceptionResponse er = new ExceptionResponse(new Date(), ex.getMessage(), ex.getBindingResult().toString());
        return new ResponseEntity(er, HttpStatus.BAD_REQUEST);
    }
}
