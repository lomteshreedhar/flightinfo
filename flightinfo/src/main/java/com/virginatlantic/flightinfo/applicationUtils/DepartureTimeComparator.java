package com.virginatlantic.flightinfo.applicationUtils;

import com.virginatlantic.flightinfo.model.FlightDetails;

import java.util.Comparator;

public class DepartureTimeComparator implements Comparator<FlightDetails> {
    /**
     * Sort the flights by departure time.
     */
    @Override
    public int compare(FlightDetails flightDetails1, FlightDetails flightDetails2) {
        return flightDetails1.getDepartureTime().compareTo(flightDetails2.getDepartureTime());
    }
}
