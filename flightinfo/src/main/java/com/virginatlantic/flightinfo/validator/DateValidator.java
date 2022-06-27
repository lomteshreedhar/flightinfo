package com.virginatlantic.flightinfo.validator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateValidator {
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
