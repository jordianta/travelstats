package com.trebol.travelstats.repositories;

import com.trebol.travelstats.domainobjects.Place;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceRepository extends JpaRepository<Place, Long> {

}
