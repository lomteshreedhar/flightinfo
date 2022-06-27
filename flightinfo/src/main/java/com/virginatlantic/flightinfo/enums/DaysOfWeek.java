package com.virginatlantic.flightinfo.enums;

public enum DaysOfWeek {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY;

    public static boolean isElementPresent(String element) {
        try {
            DaysOfWeek.valueOf(element);
            return true;
        } catch (IllegalArgumentException ie) {
            return false;
        }

    }
}
