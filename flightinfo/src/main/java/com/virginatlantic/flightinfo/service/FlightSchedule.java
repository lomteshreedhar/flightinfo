package com.virginatlantic.flightinfo.service;

import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface FlightSchedule {
    /**
     * @param localDate provided by user
     * @return returns the list of flight details filtered by day of given date.
     */

    ResponseEntity<Object> getFlights(LocalDate localDate);

}
