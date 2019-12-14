package com.trebol.travelstats.repositories;

import com.trebol.travelstats.domainobjects.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query("SELECT flight FROM Flight flight ORDER BY flight.date ASC")
    List<Flight> findAllByOrderByDateAsc();

}
