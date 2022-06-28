package com.virginatlantic.flightinfo.controller;

import com.virginatlantic.flightinfo.service.FlightScheduleImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import static com.virginatlantic.flightinfo.constants.ApplicationConstants.DATE_FORMAT;

@RestController
public class FetchFlightInfoController {
    @Autowired
    FlightScheduleImpl flightSchedule;

    /**
     * This controller method will return the flight details filtered by parameter
     * @param date provided by user
     * @return flight details list
     */
    @GetMapping("/displayAvailableFlights/{date}")
    public ResponseEntity<Object> getAvailableFlights(@PathVariable @DateTimeFormat(pattern = DATE_FORMAT) LocalDate date) {

        return flightSchedule.getFlights(date);

    }
}
