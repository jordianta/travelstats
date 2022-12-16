package com.trebol.travelstats.mappers.converters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateConverterTest {

    private DateConverter dateConverter;

    @BeforeEach
    void setUp() {
        dateConverter = new DateConverter();
    }

    @Test
    void convertTo() {
        final var calendar = Calendar.getInstance();
        calendar.set(2010, Calendar.OCTOBER, 15);
        final var date = calendar.getTime();
        final var dateConverted = dateConverter.convertTo(date, null, null);
        assertEquals("15-10-2010", dateConverted);
    }
}