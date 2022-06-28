package com.virginatlantic.flightinfo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Builder
/**
 * Response object
 */
public class FlightDetails {

    LocalTime departureTime;
    String destination;
    String destinationAirportIata;
    String flightNumber;

}
