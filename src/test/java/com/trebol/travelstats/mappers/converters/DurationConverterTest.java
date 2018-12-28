package com.trebol.travelstats.mappers.converters;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DurationConverterTest
{

    private DurationConverter durationConverter;


    @Before
    public void setUp()
    {
        durationConverter = new DurationConverter();
    }


    @Test
    public void convertTo_10minutes()
    {
        convertTo(10, "00:10:00");
    }


    @Test
    public void convertTo_1hour()
    {
        convertTo(60, "01:00:00");
    }


    @Test
    public void convertTo_2hours()
    {
        convertTo(120, "02:00:00");
    }


    @Test
    public void convertTo_11hours()
    {
        convertTo(60 * 11, "11:00:00");
    }


    @Test
    public void convertTo_13hours25minutes()
    {
        convertTo(60 * 13 + 25, "13:25:00");
    }


    @Test
    public void convertFrom_10minutes()
    {
        convertFrom("00:10:00", 10);
    }


    private void convertFrom(final String s, final int i)
    {
        final Integer durationConverted = durationConverter.convertFrom(s, null, null);
        assertEquals(Integer.valueOf(i), durationConverted);
    }


    @Test
    public void convertFrom_1hour()
    {
        convertFrom("01:00:00", 60);
    }


    @Test
    public void convertFrom_2hours()
    {
        convertFrom("02:00:00", 120);
    }


    @Test
    public void convertFrom_11hours()
    {
        convertFrom("11:00:00", 60 * 11);
    }


    @Test
    public void convertFrom_13hours25minutes()
    {
        convertFrom("13:25:00", 60 * 13 + 25);
    }


    private void convertTo(final int i, final String s)
    {
        final String durationConverted = durationConverter.convertTo(i, null, null);
        assertEquals(s, durationConverted);
    }

}