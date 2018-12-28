package com.trebol.travelstats.mappers.converters;

import java.util.Calendar;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DateConverterTest
{

    private DateConverter dateConverter;


    @Before
    public void setUp()
    {
        dateConverter = new DateConverter();
    }


    @Test
    public void convertTo()
    {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(2010, Calendar.OCTOBER, 15);
        final Date date = calendar.getTime();
        final String dateConverted = dateConverter.convertTo(date, null, null);
        assertEquals("15-10-2010", dateConverted);
    }


    @Test
    public void convertFrom()
    {
        final Date dateConverted = dateConverter.convertFrom("15-10-2010", null, null);
        final Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(2010, Calendar.OCTOBER, 15);
        assertEquals(calendar.getTime().getTime(), dateConverted.getTime());
    }

}