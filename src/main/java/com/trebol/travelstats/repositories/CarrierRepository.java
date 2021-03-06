package com.trebol.travelstats.repositories;

import com.trebol.travelstats.domainobjects.Carrier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrierRepository extends JpaRepository<Carrier, Long> {

}
