package com.virginatlantic.flightinfo.applicationUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {
    public static LocalDate parseDate(String date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(date, formatter);
    }
}
