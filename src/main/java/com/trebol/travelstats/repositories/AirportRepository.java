package com.trebol.travelstats.repositories;

import com.trebol.travelstats.domainobjects.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

}
