package com.trebol.travelstats.repositories;

import com.trebol.travelstats.domainobjects.Flight;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository
{

    List<Flight> findAll();

    void save(Flight flight);

    void delete(Long flightId);
}
