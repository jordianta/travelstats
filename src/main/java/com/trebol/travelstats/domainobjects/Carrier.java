package com.trebol.travelstats.domainobjects;

import java.util.Objects;

public class Carrier
{

    private final Long id;
    private final String name;
    private final String iataCode;


    public Carrier(final Long id, final String name, final String iataCode)
    {
        this.id = id;
        this.name = name;
        this.iataCode = iataCode;
    }


    private Carrier(final Builder builder)
    {
        id = builder.id;
        name = builder.name;
        iataCode = builder.iataCode;
    }


    public static Builder newBuilder()
    {
        return new Builder();
    }


    public Long getId()
    {
        return id;
    }


    public String getName()
    {
        return name;
    }


    public String getIataCode()
    {
        return iataCode;
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
        final Carrier carrier = (Carrier) o;
        return Objects.equals(id, carrier.id) &&
            Objects.equals(name, carrier.name) &&
            Objects.equals(iataCode, carrier.iataCode);
    }


    @Override
    public int hashCode()
    {

        return Objects.hash(id, name, iataCode);
    }


    public static final class Builder
    {
        private Long id;
        private String name;
        private String iataCode;


        private Builder()
        {
        }


        public Builder withId(final Long id)
        {
            this.id = id;
            return this;
        }


        public Builder withName(final String name)
        {
            this.name = name;
            return this;
        }


        public Builder withIataCode(final String iataCode)
        {
            this.iataCode = iataCode;
            return this;
        }


        public Carrier build()
        {
            return new Carrier(this);
        }
    }
}