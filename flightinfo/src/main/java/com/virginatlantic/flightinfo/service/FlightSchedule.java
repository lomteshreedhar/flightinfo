package com.virginatlantic.flightinfo.service;

import org.springframework.http.ResponseEntity;

public interface FlightSchedule {
    /**
     * @param dateTime
     * @return returns the list of flight details filtered by day of given date.
     */

    ResponseEntity<Object> getFlights(String dateTime);

}
