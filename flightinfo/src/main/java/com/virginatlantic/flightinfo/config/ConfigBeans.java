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

    // This hashMap will store Key as Day i.e. SUNDAY and value as List of all flights departing on SUNDAY
    private final Map<String, List<FlightDetails>> flightsOnGivenDay = new HashMap<>();

    /**
     * Reads and parse csv file.
     * @return FlightDataList consists of list of day wise list of flight details
     * @throws Exception in case of any exception happens while file read or parsing
     */
    @Bean
    FlightDataList flightDataList() throws Exception {
        try {
            InputStream is = new ClassPathResource("flight_data.csv").getInputStream();
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

    /**
     * Populates flightsOnGivenDay list which maintains day wise flight info list
     * @param flightData list of parsed flight list from file
     * @throws IllegalAccessException for any exception while operating on reflection
     */
    private void populateFlightsOnGivenDay(List<FlightData> flightData) throws IllegalAccessException {
        for (FlightData flight : flightData) {
            // for each FlightData object identify which field has value 'X' populated with help of reflection
            List<Field> fields = List.of(flight.getClass().getDeclaredFields());
            for (Field field : fields) {
                if (DaysOfWeek.isElementPresent(field.getName().toUpperCase())
                        && (getFieldData(field, flight)).equalsIgnoreCase("X")) {
                    //check the hashmap key i.e. day name already present then append FlightDetails object in the list
                    if (flightsOnGivenDay.get(field.getName()) != null) {
                        flightsOnGivenDay.get(field.getName()).add(populateFlight(flight));
                    } else {
                        // create new element (key,value) in hashmap
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

