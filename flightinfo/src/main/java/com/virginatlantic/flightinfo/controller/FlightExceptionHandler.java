package com.virginatlantic.flightinfo.controller;

import com.virginatlantic.flightinfo.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


    @ControllerAdvice
    public class FlightExceptionHandler extends ResponseEntityExceptionHandler {

        @ExceptionHandler(Exception.class)
        public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
            return new ResponseEntity("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @ExceptionHandler(ResourceNotFoundException.class)
        public final ResponseEntity<Object> handleUserNotFoundException(ResourceNotFoundException ex) {
            return new ResponseEntity("No Flight Available", HttpStatus.NOT_FOUND);
        }
}
