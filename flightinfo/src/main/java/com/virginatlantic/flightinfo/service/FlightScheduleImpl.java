package com.virginatlantic.flightinfo.service;

import com.virginatlantic.flightinfo.applicationUtils.DepartureTimeComparator;
import com.virginatlantic.flightinfo.applicationUtils.Utils;
import com.virginatlantic.flightinfo.model.FlightDataList;
import com.virginatlantic.flightinfo.model.FlightDetails;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.virginatlantic.flightinfo.constants.ApplicationConstants.DATE_FORMAT;


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
            LocalDate localDate = Utils.parseDate(dateTime, DATE_FORMAT);

            List<FlightDetails> flightDetailsList = flightDataList.getFlightsOnGivenDay()
                    .get(localDate.getDayOfWeek().name().toLowerCase());

            if (!CollectionUtils.isEmpty(flightDetailsList)) {
                return ResponseEntity.ok().body(flightDetailsList.stream()
                        .sorted(new DepartureTimeComparator())
                        .collect(Collectors.toList()));
            } else {
                return ResponseEntity.notFound().build();
            }

    }
}
