package com.trebol.travelstats.repositories;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.trebol.travelstats.domainobjects.Airport;
import com.trebol.travelstats.domainobjects.Carrier;
import com.trebol.travelstats.domainobjects.Flight;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class FlightRepositoryImpl implements FlightRepository
{

    private static final String KEY = Flight.class.getSimpleName();
    private static final String DESTINATION = "destination";
    private static final String ORIGIN = "origin";
    private static final String NUMBER = "number";
    private static final String DISTANCE = "distance";
    private static final String CARRIER = "carrier";
    private static final String DURATION = "duration";
    private static final String DATE = "date";

    private final DatastoreService datastoreService;
    private final CarrierRepository carrierRepository;
    private final AirportRepository airportRepository;


    public FlightRepositoryImpl(
        final DatastoreService datastoreService,
        final CarrierRepository carrierRepository,
        final AirportRepository airportRepository)
    {
        this.datastoreService = datastoreService;
        this.carrierRepository = carrierRepository;
        this.airportRepository = airportRepository;
    }


    @Override
    public List<Flight> findAll()
    {
        final List<Flight> flightList = new ArrayList<>();

        datastoreService.prepare(new Query(KEY))
            .asIterable()
            .forEach(entity -> flightList.add(entityToFlight(entity)));

        return flightList;
    }


    @Override
    public void save(final Flight flight)
    {
        datastoreService.put(flightToEntity(flight));
    }


    @Override
    public void delete(final Long id)
    {
        datastoreService.delete(KeyFactory.createKey(KEY, id));
    }


    private Flight entityToFlight(final Entity entity)
    {
        return Flight.newBuilder()
            .withId(entity.getKey().getId())
            .withDate((Date) entity.getProperty(DATE))
            .withCarrier(getCarrier(entity.getProperty(CARRIER)))
            .withDestination(getAirport(entity.getProperty(DESTINATION)))
            .withDuration((Integer) entity.getProperty(DURATION))
            .withDistance((Integer) entity.getProperty(DISTANCE))
            .withNumber((String) entity.getProperty(NUMBER))
            .withOrigin(getAirport(entity.getProperty(ORIGIN)))
            .build();
    }


    private Airport getAirport(final Object airportKey)
    {
        return airportRepository.find((Long) airportKey).orElse(null);
    }


    private Carrier getCarrier(final Object carrierKey)
    {
        return carrierRepository.find((Long) carrierKey).orElse(null);
    }


    private Entity flightToEntity(final Flight flight)
    {
        final Entity entity = new Entity(KEY);
        entity.setProperty(CARRIER, flight.getCarrier().getId());
        entity.setProperty(DATE, flight.getDate());
        entity.setProperty(DESTINATION, flight.getDestination().getId());
        entity.setProperty(DISTANCE, flight.getDistance());
        entity.setProperty(DURATION, flight.getDuration());
        entity.setProperty(NUMBER, flight.getNumber());
        entity.setProperty(ORIGIN, flight.getOrigin().getId());
        return entity;
    }
}
