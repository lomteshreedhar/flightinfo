package com.virginatlantic.flightinfo.service;

import com.virginatlantic.flightinfo.ApplicationUtils.DepartureTimeComparator;
import com.virginatlantic.flightinfo.ApplicationUtils.Utils;
import com.virginatlantic.flightinfo.model.FlightDataList;
import com.virginatlantic.flightinfo.model.FlightDetails;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.virginatlantic.flightinfo.constants.ApplicationConstants.*;

@Service
public class FlightScheduleImpl implements FlightSchedule {

    @Autowired
    FlightDataList flightDataList;

    /**
     * Fetches list of flights by day of given date.List flightDataList already contain
     * list of flights by day.
     *
     * @param dateTime provided by user
     * @return returns the list of flight details filtered by day of given date.
     */

    @Override
    public ResponseEntity<Object> getFlights(String dateTime) {
            LocalDateTime localDateTime = Utils.parseDate(dateTime, DATEFORMAT);

            List<FlightDetails> flightDetailsList = flightDataList.getFlightsOnGivenDay()
                    .get(localDateTime.getDayOfWeek().name().toLowerCase());

            if (!CollectionUtils.isEmpty(flightDetailsList)) {
                return ResponseEntity.ok().body(flightDetailsList.stream()
                        .sorted(new DepartureTimeComparator())
                        .collect(Collectors.toList()));
            } else {
                return ResponseEntity.notFound().build();
            }

    }
}
