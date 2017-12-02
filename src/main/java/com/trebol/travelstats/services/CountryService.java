package com.trebol.travelstats.services;

import com.trebol.travelstats.datatransferobjects.CountryDTO;

import java.util.List;

public interface CountryService {

    List<CountryDTO> getAllCountries();
}
