package com.virginatlantic.flightinfo.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateValidator {
    /**
     *
     * @param date date provided by user
     * @param format for date
     * @return true if date valid else returns false
     */
    public static boolean isValidDate(String date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        try {
            LocalDate.parse(date, formatter);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
