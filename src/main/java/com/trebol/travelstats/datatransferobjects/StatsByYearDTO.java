package com.trebol.travelstats.datatransferobjects;

import java.util.Objects;

public class StatsByYearDTO
{

    private final Integer year;
    private final Integer flights;
    private final Integer distance;


    public StatsByYearDTO(final Integer year, final Integer flights, final Integer distance)
    {
        this.year = year;
        this.flights = flights;
        this.distance = distance;
    }


    public Integer getYear()
    {
        return year;
    }


    public Integer getFlights()
    {
        return flights;
    }


    public Integer getDistance()
    {
        return distance;
    }


    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        final StatsByYearDTO that = (StatsByYearDTO) o;
        return Objects.equals(year, that.year) &&
            Objects.equals(flights, that.flights) &&
            Objects.equals(distance, that.distance);
    }


    @Override
    public int hashCode()
    {
        return Objects.hash(year, flights, distance);
    }
}
