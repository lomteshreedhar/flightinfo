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

        ResponseEntity responseEntity = fetchFlightInfoController.getAvailableFlights(
                "1986-04-21 12:30:30");
        Assertions.assertEquals("200 OK", responseEntity.getStatusCode().toString());

    }

    @Test
    void shouldReturnBadRequestForInvalidDate() {

        ResponseEntity responseEntity = fetchFlightInfoController.getAvailableFlights(
                "1986-04-21Bad 12:30:30");
        Assertions.assertEquals("400 BAD_REQUEST", responseEntity.getStatusCode().toString());
        Assertions.assertEquals(ApplicationConstants.INVALID_DATE, responseEntity.getBody());

    }

    @Test
    void shouldReturnBadRequestForNullDate() {

        ResponseEntity responseEntity = fetchFlightInfoController.getAvailableFlights(
                null);
        Assertions.assertEquals("400 BAD_REQUEST", responseEntity.getStatusCode().toString());
        Assertions.assertEquals(ApplicationConstants.INVALID_DATE, responseEntity.getBody());

    }

    private ResponseEntity<Object> buildResponseForGoodInputs() {
        ArrayList<FlightDetails> detailsArrayList = new ArrayList<>();
        detailsArrayList.add(FlightDetails.builder().flightNumber("VS303").build());
        detailsArrayList.add(FlightDetails.builder().flightNumber("VS303").build());
        detailsArrayList.add(FlightDetails.builder().flightNumber("VS305").build());
        return ResponseEntity.ok().body(detailsArrayList);
    }

}

