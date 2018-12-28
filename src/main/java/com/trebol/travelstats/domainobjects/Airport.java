package com.trebol.travelstats.domainobjects;

public class Airport
{

    private final Long id;
    private final String name;
    private final Float latitude;
    private final Float longitude;
    private final String city;
    private final String iataCode;
    private final Country country;


    public Long getId()
    {
        return id;
    }


    public String getName()
    {
        return name;
    }


    public Float getLatitude()
    {
        return latitude;
    }


    public Float getLongitude()
    {
        return longitude;
    }


    public String getCity()
    {
        return city;
    }


    public String getIataCode()
    {
        return iataCode;
    }


    public Country getCountry()
    {
        return country;
    }


    public Airport(final Long id, final String name, final Float latitude, final Float longitude, final String city, final String iataCode, final Country country)
    {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
        this.iataCode = iataCode;
        this.country = country;
    }


    private Airport(final Builder builder)
    {
        id = builder.id;
        name = builder.name;
        latitude = builder.latitude;
        longitude = builder.longitude;
        city = builder.city;
        iataCode = builder.iataCode;
        country = builder.country;
    }


    public static Builder newBuilder()
    {
        return new Builder();
    }


    public static final class Builder
    {
        private Long id;
        private String name;
        private Float latitude;
        private Float longitude;
        private String city;
        private String iataCode;
        private Country country;


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


        public Builder withLatitude(final Float latitude)
        {
            this.latitude = latitude;
            return this;
        }


        public Builder withLongitude(final Float longitude)
        {
            this.longitude = longitude;
            return this;
        }


        public Builder withCity(final String city)
        {
            this.city = city;
            return this;
        }


        public Builder withIataCode(final String iataCode)
        {
            this.iataCode = iataCode;
            return this;
        }


        public Builder withCountry(final Country country)
        {
            this.country = country;
            return this;
        }


        public Airport build()
        {
            return new Airport(this);
        }
    }
}
