package com.trebol.travelstats.repositories;

import com.trebol.travelstats.domainobjects.Carrier;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrierRepository
{

    List<Carrier> findAll();

    Optional<Carrier> find(Long carrierKey);

    void add(List<Carrier> carrierList);
}
