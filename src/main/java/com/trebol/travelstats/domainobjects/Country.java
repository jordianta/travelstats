package com.trebol.travelstats.domainobjects;

public class Country
{
    private final Long id;
    private final String name;
    private final Integer continentId;
    private final String isoCode;


    public Country(final Long id, final String name, final Integer continentId, final String isoCode)
    {
        this.id = id;
        this.name = name;
        this.continentId = continentId;
        this.isoCode = isoCode;
    }


    private Country(final Builder builder)
    {
        id = builder.id;
        name = builder.name;
        continentId = builder.continentId;
        isoCode = builder.isoCode;
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


    public Integer getContinentId()
    {
        return continentId;
    }


    public String getIsoCode()
    {
        return isoCode;
    }


    public static final class Builder
    {
        private Long id;
        private String name;
        private Integer continentId;
        private String isoCode;


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


        public Builder withContinentId(final Integer continentId)
        {
            this.continentId = continentId;
            return this;
        }


        public Builder withIsoCode(final String isoCode)
        {
            this.isoCode = isoCode;
            return this;
        }


        public Country build()
        {
            return new Country(this);
        }
    }
}
