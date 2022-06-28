package com.virginatlantic.flightinfo.validator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateValidator {
    /**
     *
     * @param date date provided by user
     * @param format
     * @return true if date valid else returns false
     */
    public static boolean isValidDate(String date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        try {
            LocalDateTime.parse(date, formatter);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
