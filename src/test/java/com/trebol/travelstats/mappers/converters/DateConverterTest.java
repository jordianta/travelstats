package com.trebol.travelstats.mappers.converters;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DateConverterTest {

    private DateConverter dateConverter;

    @Before
    public void setUp() throws Exception {
        dateConverter = new DateConverter();
    }

    @Test
    public void convertTo() throws Exception {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(2010, Calendar.OCTOBER, 15);
        final Date date = calendar.getTime();
        final String dateConverted = dateConverter.convertTo(date, null, null);
        assertEquals("15-10-2010", dateConverted);
    }

    @Test
    public void convertFrom() throws Exception {
    }

}