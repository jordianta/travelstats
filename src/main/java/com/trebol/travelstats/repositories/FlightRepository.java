package com.trebol.travelstats.repositories;

import com.trebol.travelstats.domainobjects.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

}
