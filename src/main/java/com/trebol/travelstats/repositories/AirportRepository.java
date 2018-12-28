package com.trebol.travelstats.repositories;

import com.trebol.travelstats.domainobjects.Airport;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository
{

    List<Airport> findAll();

    Optional<Airport> find(Long id);
}
