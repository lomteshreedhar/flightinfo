package com.virginatlantic.flightinfo.service;

import com.virginatlantic.flightinfo.enums.DaysOfWeek;
import com.virginatlantic.flightinfo.exception.ResourceNotFoundException;
import com.virginatlantic.flightinfo.model.FlightDataList;
import com.virginatlantic.flightinfo.model.FlightDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
public class FlightScheduleImplTest {

    @Mock
    FlightDataList flightDataList;
    @InjectMocks
    FlightScheduleImpl schedule;

    @Test
    void shouldReturnListOfFlights() {
        Mockito.when(flightDataList.getFlightsOnGivenDay()).thenReturn(buildFlightDataList());
        LocalDate localDate = LocalDate.of(2021, 8, 23);
        //Monday
        ResponseEntity responseEntity = schedule.getFlights(localDate);
        Assertions.assertEquals("200 OK", responseEntity.getStatusCode().toString());
        List<FlightDetails> flightDetails = (List<FlightDetails>) responseEntity.getBody();
        Assertions.assertEquals(2, flightDetails.size());

        //Friday
        localDate = LocalDate.of(2021, 8, 20);
        schedule.getFlights(localDate);
        Assertions.assertEquals("200 OK", responseEntity.getStatusCode().toString());
        flightDetails = (List<FlightDetails>) responseEntity.getBody();
        Assertions.assertEquals(2, flightDetails.size());

    }

    @Test
    void shouldNotReturnListOfFlights() {
        Mockito.when(flightDataList.getFlightsOnGivenDay()).thenReturn(buildFlightDataList());
        LocalDate localDate = LocalDate.of(2021, 8, 22);
        //Sunday
        Assertions.assertThrows(ResourceNotFoundException.class,()-> schedule.getFlights(localDate));

    }

    private Map<String, List<FlightDetails>> buildFlightDataList() {
        Map<String, List<FlightDetails>> flightMap = new HashMap<>();
        flightMap.put(DaysOfWeek.MONDAY.name().toLowerCase(), new ArrayList<>() {{
            add(FlightDetails.builder().flightNumber("VS2001")
                    .destination("Bahamas")
                    .departureTime(LocalTime.of(01,01))
                    .destinationAirportIata("LGW").build());
            add(FlightDetails.builder().flightNumber("VS2002")
                    .destination("Barbadosa")
                    .departureTime(LocalTime.of(02,01))
                    .destinationAirportIata("BAR").build());
        }});

        flightMap.put(DaysOfWeek.FRIDAY.name().toLowerCase(), new ArrayList<>() {{
            add(FlightDetails.builder().flightNumber("VS2003")
                    .destination("Honolulu")
                    .departureTime(LocalTime.of(01,01))
                    .destinationAirportIata("HON").build());
            add(FlightDetails.builder().flightNumber("VS2004")
                    .destination("Goa")
                    .departureTime(LocalTime.of(02,01))
                    .destinationAirportIata("Goa").build());
        }});
        return flightMap;
    }
}
