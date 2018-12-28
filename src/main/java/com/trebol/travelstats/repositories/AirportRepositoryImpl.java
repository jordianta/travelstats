package com.trebol.travelstats.repositories;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.trebol.travelstats.domainobjects.Airport;
import com.trebol.travelstats.domainobjects.Country;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class AirportRepositoryImpl implements AirportRepository
{

    private static final String KEY = Airport.class.getSimpleName();
    private static final String CITY = "city";
    private static final String COUNTRY = "country";
    private static final String IATA_CODE = "iataCode";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String NAME = "name";

    private static final String COUNTRY_NAME = "name";
    private static final String CONTINENT_ID = "continentId";
    private static final String ISO_CODE = "isoCode";

    private final DatastoreService datastoreService;


    public AirportRepositoryImpl(final DatastoreService datastoreService)
    {
        this.datastoreService = datastoreService;
    }


    @Override
    public List<Airport> findAll()
    {
        final List<Airport> airportList = new ArrayList<>();

        datastoreService.prepare(new Query(KEY))
            .asIterable()
            .forEach(entity -> airportList.add(entityToAirport(entity)));

        return airportList;
    }


    @Override
    public Optional<Airport> find(final Long id)
    {
        try
        {
            return Optional.of(entityToAirport(datastoreService.get(KeyFactory.createKey(KEY, id))));
        }
        catch (EntityNotFoundException e)
        {
            return Optional.empty();
        }
    }


    private Airport entityToAirport(final Entity entity)
    {
        return Airport.newBuilder()
            .withId(entity.getKey().getId())
            .withCity((String) entity.getProperty(CITY))
            .withCountry(getCountry(entity))
            .withIataCode((String) entity.getProperty(IATA_CODE))
            .withLatitude((Float) entity.getProperty(LATITUDE))
            .withLongitude((Float) entity.getProperty(LONGITUDE))
            .withName((String) entity.getProperty(NAME))
            .build();
    }


    private Country getCountry(final Entity entity)
    {
        final EmbeddedEntity embeddedCountry = (EmbeddedEntity) entity.getProperty(COUNTRY);

        return Country.newBuilder()
            .withId(embeddedCountry.getKey().getId())
            .withName((String) embeddedCountry.getProperty(COUNTRY_NAME))
            .withContinentId((Integer) embeddedCountry.getProperty(CONTINENT_ID))
            .withIsoCode((String) embeddedCountry.getProperty(ISO_CODE))
            .build();
    }
}
