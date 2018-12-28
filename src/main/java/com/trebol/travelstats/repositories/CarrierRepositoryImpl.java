package com.trebol.travelstats.repositories;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.trebol.travelstats.domainobjects.Carrier;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class CarrierRepositoryImpl implements CarrierRepository
{

    private static final String KEY = Carrier.class.getSimpleName();
    private static final String IATA_CODE = "iataCode";
    private static final String NAME = "name";

    private final DatastoreService datastoreService;


    public CarrierRepositoryImpl(final DatastoreService datastoreService)
    {
        this.datastoreService = datastoreService;
    }


    @Override
    public List<Carrier> findAll()
    {
        final List<Carrier> carrierList = new ArrayList<>();

        datastoreService.prepare(new Query(KEY))
            .asIterable()
            .forEach(entity -> carrierList.add(entityToCarrier(entity)));

        return carrierList;
    }


    @Override
    public Optional<Carrier> find(final Long id)
    {
        try
        {
            return Optional.of(entityToCarrier(datastoreService.get(KeyFactory.createKey(KEY, id))));
        }
        catch (EntityNotFoundException e)
        {
            return Optional.empty();
        }
    }


    private Carrier entityToCarrier(final Entity entity)
    {
        return Carrier.newBuilder()
            .withId(entity.getKey().getId())
            .withName((String) entity.getProperty(NAME))
            .withIataCode((String) entity.getProperty(IATA_CODE))
            .build();
    }
}
