package com.virginatlantic.flightinfo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class FlightDataList {
    List<FlightData> flightDataList;
    Map<String, List<FlightDetails>> flightsOnGivenDay;
}
