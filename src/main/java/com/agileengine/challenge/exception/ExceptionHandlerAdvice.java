package com.agileengine.challenge.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.hibernate.PessimisticLockException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    private HttpHeaders setHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> exceptionHandler(Exception e){
        HttpHeaders headers = setHeaders();
        Error error = new Error(HttpStatus.BAD_REQUEST.value(), "Something unspected ocurred, please try again!");
        return new ResponseEntity<>(error, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Error> exceptionHandler(ApiException e){
        HttpHeaders headers = setHeaders();
        return new ResponseEntity<>(e.getResponse(), headers, HttpStatus.valueOf(e.getResponse().getCode()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Error> exceptionHandler(MissingServletRequestParameterException e){
        HttpHeaders headers = setHeaders();
        Error error = new Error(HttpStatus.BAD_REQUEST.value(), "ID is required ");
        return new ResponseEntity<>(error, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Error> exceptionHandler(MethodArgumentTypeMismatchException e){
        HttpHeaders headers = setHeaders();
        Error error = new Error(HttpStatus.BAD_REQUEST.value(), "You had enter an invalid format in: " + e.getName());
        return new ResponseEntity<>(error, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> exceptionHandler(MethodArgumentNotValidException e){
        HttpHeaders headers = setHeaders();
        Error error = new Error(HttpStatus.BAD_REQUEST.value(), e.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
        return new ResponseEntity<>(error, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PessimisticLockException.class)
    public ResponseEntity<Error> exceptionHandler(PessimisticLockException e) {
        HttpHeaders headers = setHeaders();
        Error error = new Error(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return new ResponseEntity<>(error, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Error> exceptionHandler(HttpMessageNotReadableException e){
        HttpHeaders headers = setHeaders();
        Error error = new Error(HttpStatus.BAD_REQUEST.value(), "You had enter an invalid format in: " +
                ((InvalidFormatException) e.getCause()).getPath().get(0).getFieldName());
        return new ResponseEntity<>(error, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NoHandlerFoundException.class, HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<Error> exceptionHandler(Exception e, WebRequest request) {
        HttpHeaders headers = setHeaders();
        Error response = new Error(HttpStatus.NOT_FOUND.value(), "Resource not found.");
        return new ResponseEntity<>(response, headers, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<Error> exceptionHandler(JsonParseException e) {
        HttpHeaders headers = setHeaders();
        Error response = new Error(HttpStatus.BAD_REQUEST.value(), "Json format is invalid");
        return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Error> exceptionHandler(ConstraintViolationException e) {
        HttpHeaders headers = setHeaders();
        Error response = new Error(HttpStatus.BAD_REQUEST.value(), "Json format is invalid");
        return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);
    }



}