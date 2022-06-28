package com.virginatlantic.flightinfo.controller;

import com.virginatlantic.flightinfo.constants.ApplicationConstants;
import com.virginatlantic.flightinfo.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


    @ControllerAdvice
    public class FlightExceptionHandler extends ResponseEntityExceptionHandler {

        @ExceptionHandler(Exception.class)
        public final ResponseEntity<String> handleAllExceptions(Exception ex) {
            return ResponseEntity.internalServerError().body("Internal Server Error");
        }

        @ExceptionHandler(ResourceNotFoundException.class)
        public final ResponseEntity<String> handleUserNotFoundException(ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Flight Available");
        }
}
