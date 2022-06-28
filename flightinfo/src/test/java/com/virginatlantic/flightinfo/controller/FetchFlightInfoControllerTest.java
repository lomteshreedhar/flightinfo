package com.virginatlantic.flightinfo.controller;

import com.virginatlantic.flightinfo.constants.ApplicationConstants;
import com.virginatlantic.flightinfo.model.FlightDetails;
import com.virginatlantic.flightinfo.service.FlightScheduleImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The goal of this test class is to cover all possible conditions(mutation testing)
 */
@ExtendWith(SpringExtension.class)
public class FetchFlightInfoControllerTest {

    @Mock
    FlightScheduleImpl flightSchedule;

    @InjectMocks
    FetchFlightInfoController fetchFlightInfoController;

    @Test
    void shouldReturnFlightsData() {
        Mockito.when(flightSchedule.getFlights(ArgumentMatchers.any()))
                .thenReturn(buildResponseForGoodInputs());
        LocalDate localDate = LocalDate.of(2020, 1, 8);
        ResponseEntity responseEntity = fetchFlightInfoController.getAvailableFlights(
                localDate);
        Assertions.assertEquals("200 OK", responseEntity.getStatusCode().toString());

    }

    @Test
    void shouldReturnBadRequestForNullDate() {
       Object response = fetchFlightInfoController.getAvailableFlights(null);
       Assertions.assertNull(response);
    }

    private ResponseEntity<Object> buildResponseForGoodInputs() {
        ArrayList<FlightDetails> detailsArrayList = new ArrayList<>();
        detailsArrayList.add(FlightDetails.builder().flightNumber("VS303").build());
        detailsArrayList.add(FlightDetails.builder().flightNumber("VS303").build());
        detailsArrayList.add(FlightDetails.builder().flightNumber("VS305").build());
        return ResponseEntity.ok().body(detailsArrayList);
    }

}

