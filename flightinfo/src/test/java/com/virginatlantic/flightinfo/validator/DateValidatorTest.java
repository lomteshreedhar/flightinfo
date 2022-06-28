package com.virginatlantic.flightinfo.validator;

import com.virginatlantic.flightinfo.constants.ApplicationConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DateValidatorTest {
    @Test
    void shouldReturnTrueForValidDate() {
        String date = "2021-08-22 12:30:30";
        Assertions.assertTrue(DateValidator.isValidDate(date, ApplicationConstants.DATE_FORMAT));

    }

    @Test
    void shouldReturnFalseForInValidDate() {
        String date = "2021-08-22bad 12:30:30";
        Assertions.assertFalse(DateValidator.isValidDate(date, ApplicationConstants.DATE_FORMAT));
    }
}
