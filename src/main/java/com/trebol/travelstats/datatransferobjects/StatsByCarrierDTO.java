package com.trebol.travelstats.datatransferobjects;

import java.util.Objects;

public class StatsByCarrierDTO
{

    private final String carrier;
    private final Integer flights;
    private final Integer distance;
    private final Integer average;


    public StatsByCarrierDTO(final String carrier, final Integer flights, final Integer distance, final Integer average)
    {
        this.carrier = carrier;
        this.flights = flights;
        this.distance = distance;
        this.average = average;
    }


    public String getCarrier()
    {
        return carrier;
    }


    public Integer getFlights()
    {
        return flights;
    }


    public Integer getDistance()
    {
        return distance;
    }


    public Integer getAverage()
    {
        return average;
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
        final StatsByCarrierDTO that = (StatsByCarrierDTO) o;
        return Objects.equals(carrier, that.carrier) &&
            Objects.equals(flights, that.flights) &&
            Objects.equals(distance, that.distance) &&
            Objects.equals(average, that.average);
    }


    @Override
    public int hashCode()
    {

        return Objects.hash(carrier, flights, distance, average);
    }
}
