package com.virginatlantic.flightinfo.ApplicationUtils;

import com.virginatlantic.flightinfo.model.FlightDetails;

import java.util.Comparator;

public class DepartureTimeComparator implements Comparator<FlightDetails> {
    /**
     * Sort the flights by departure time.
     * @param o1
     * @param o2
     * @return sorted order
     */
    @Override
    public int compare(FlightDetails o1, FlightDetails o2) {
        return o1.getDepartureTime().compareTo(o2.getDepartureTime());
    }
}
