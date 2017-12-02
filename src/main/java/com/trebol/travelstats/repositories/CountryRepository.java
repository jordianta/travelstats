package com.trebol.travelstats.repositories;

import com.trebol.travelstats.domainobjects.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

}
