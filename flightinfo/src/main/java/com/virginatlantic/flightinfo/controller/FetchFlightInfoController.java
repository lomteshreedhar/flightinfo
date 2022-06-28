package com.virginatlantic.flightinfo.controller;

import com.virginatlantic.flightinfo.constants.ApplicationConstants;
import com.virginatlantic.flightinfo.service.FlightScheduleImpl;
import com.virginatlantic.flightinfo.validator.DateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Object> getAvailableFlights(@PathVariable String date) {

        if (!DateValidator.isValidDate(date, ApplicationConstants.DATE_FORMAT)) {
            return ResponseEntity.badRequest().body(ApplicationConstants.INVALID_DATE);
        }

        return flightSchedule.getFlights(date);

    }
}
