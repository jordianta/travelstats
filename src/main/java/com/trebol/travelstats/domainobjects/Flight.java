package com.trebol.travelstats.domainobjects;

import java.util.Date;

public class Flight
{

    private final Long id;
    private final Airport origin;
    private final Airport destination;
    private final Carrier carrier;
    private final Date date;
    private final Integer distance;
    private final Integer duration;
    private final String number;


    public Flight(
        final Long id,
        final Airport origin,
        final Airport destination,
        final Carrier carrier,
        final Date date,
        final Integer distance,
        final Integer duration,
        final String number)
    {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.carrier = carrier;
        this.date = date;
        this.distance = distance;
        this.duration = duration;
        this.number = number;
    }


    private Flight(final Builder builder)
    {
        id = builder.id;
        origin = builder.origin;
        destination = builder.destination;
        carrier = builder.carrier;
        date = builder.date;
        distance = builder.distance;
        duration = builder.duration;
        number = builder.number;
    }


    public static Builder newBuilder()
    {
        return new Builder();
    }


    public Long getId()
    {
        return id;
    }


    public Airport getOrigin()
    {
        return origin;
    }


    public Airport getDestination()
    {
        return destination;
    }


    public Carrier getCarrier()
    {
        return carrier;
    }


    public Date getDate()
    {
        return date;
    }


    public Integer getDistance()
    {
        return distance;
    }


    public Integer getDuration()
    {
        return duration;
    }


    public String getNumber()
    {
        return number;
    }


    public static final class Builder
    {
        private Long id;
        private Airport origin;
        private Airport destination;
        private Carrier carrier;
        private Date date;
        private Integer distance;
        private Integer duration;
        private String number;


        private Builder()
        {
        }


        public Builder withId(final Long id)
        {
            this.id = id;
            return this;
        }


        public Builder withOrigin(final Airport origin)
        {
            this.origin = origin;
            return this;
        }


        public Builder withDestination(final Airport destination)
        {
            this.destination = destination;
            return this;
        }


        public Builder withCarrier(final Carrier carrier)
        {
            this.carrier = carrier;
            return this;
        }


        public Builder withDate(final Date date)
        {
            this.date = date;
            return this;
        }


        public Builder withDistance(final Integer distance)
        {
            this.distance = distance;
            return this;
        }


        public Builder withDuration(final Integer duration)
        {
            this.duration = duration;
            return this;
        }


        public Builder withNumber(final String number)
        {
            this.number = number;
            return this;
        }


        public Flight build()
        {
            return new Flight(this);
        }
    }
}
