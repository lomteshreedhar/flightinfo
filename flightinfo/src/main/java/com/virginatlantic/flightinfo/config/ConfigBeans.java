package com.virginatlantic.flightinfo.config;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.virginatlantic.flightinfo.constants.ApplicationConstants;
import com.virginatlantic.flightinfo.enums.DaysOfWeek;
import com.virginatlantic.flightinfo.model.FlightData;
import com.virginatlantic.flightinfo.model.FlightDataList;
import com.virginatlantic.flightinfo.model.FlightDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@Slf4j
public class ConfigBeans {
    private final Map<String, List<FlightDetails>> flightsOnGivenDay = new HashMap<>();

    @Bean
    FlightDataList flightDataList() throws Exception {
        try {
            InputStream is = new ClassPathResource("flights.csv").getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is,
                    StandardCharsets.UTF_8));
            CsvToBean<FlightData> csvToBean = new CsvToBeanBuilder<FlightData>(br)
                    .withType(FlightData.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<FlightData> flightData = csvToBean.parse();
            populateFlightsOnGivenDay(flightData);
            return FlightDataList.builder()
                    .flightDataList(flightData)
                    .flightsOnGivenDay(getFlightsOnGivenDay())
                    .build();

        } catch (Exception e) {
            log.error(ApplicationConstants.INITIALISATION_FAILED, e);
            throw new Exception(ApplicationConstants.INITIALISATION_FAILED);
        }
    }

    private void populateFlightsOnGivenDay(List<FlightData> flightData) throws IllegalAccessException {
        for (FlightData flight : flightData) {
            List<Field> fields = List.of(flight.getClass().getDeclaredFields());
            for (Field field : fields) {
                if (DaysOfWeek.isElementPresent(field.getName().toUpperCase())
                        && (getFieldData(field, flight)).equalsIgnoreCase("X")) {
                    if (flightsOnGivenDay.get(field.getName()) != null) {
                        flightsOnGivenDay.get(field.getName()).add(populateFlight(flight));
                    } else {
                        flightsOnGivenDay.put(field.getName(), new ArrayList<>() {
                                    {
                                        add(populateFlight(flight));
                                    }
                                }
                        );
                    }
                }
            }
        }
    }

    private FlightDetails populateFlight(FlightData flight) {
        return FlightDetails.builder()
                .departureTime(flight.getDepartureTime())
                .flightNumber(flight.getFlightNumber())
                .destination(flight.getDestination())
                .destinationAirportIata(flight.getDestinationAirportIata())
                .build();
    }

    private String getFieldData(Field field, Object object) throws IllegalAccessException {
        field.setAccessible(true);
        return field.get(object) instanceof String ? field.get(object).toString() : "";
    }

    public Map<String, List<FlightDetails>> getFlightsOnGivenDay() {
        return flightsOnGivenDay;
    }

}

