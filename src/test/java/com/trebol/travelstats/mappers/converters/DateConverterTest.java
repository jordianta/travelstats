package com.trebol.travelstats.mappers.converters;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

public class DateConverterTest {

    private DateConverter dateConverter;

    @Before
    public void setUp() {
        dateConverter = new DateConverter();
    }

    @Test
    public void convertTo() {
        final var calendar = Calendar.getInstance();
        calendar.set(2010, Calendar.OCTOBER, 15);
        final var date = calendar.getTime();
        final var dateConverted = dateConverter.convertTo(date, null, null);
        assertEquals("15-10-2010", dateConverted);
    }
}