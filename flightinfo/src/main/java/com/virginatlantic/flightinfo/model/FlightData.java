package com.virginatlantic.flightinfo.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter

/**
 * Model class for reading the CSV into Java object.
 */

public class FlightData {

    @CsvBindByName(column = "Departure Time")
    String departureTimeColumn;
    @CsvBindByName(column = "Destination")
    String destination;
    @CsvBindByName(column = "Destination Airport IATA")
    String destinationAirportIata;
    @CsvBindByName(column = "Flight No")
    String flightNumber;
    @CsvBindByName(column = "Sunday")
    String sunday;
    @CsvBindByName(column = "Monday")
    String monday;
    @CsvBindByName(column = "Tuesday")
    String tuesday;
    @CsvBindByName(column = "Wednesday")
    String wednesday;
    @CsvBindByName(column = "Thursday")
    String thursday;
    @CsvBindByName(column = "Friday")
    String friday;
    @CsvBindByName(column = "Saturday")
    String saturday;

    LocalTime departureTime;

    public void setDepartureTimeColumn(String departureTimeColumn) {
        this.departureTimeColumn = departureTimeColumn;
        this.departureTime = LocalTime.of(Integer.parseInt(departureTimeColumn.trim().split(":")[0]),
                Integer.parseInt(departureTimeColumn.trim().split(":")[1]));
    }
}

