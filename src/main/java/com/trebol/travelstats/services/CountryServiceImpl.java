package com.trebol.travelstats.services;

import com.trebol.travelstats.datatransferobjects.CountryDTO;
import com.trebol.travelstats.mappers.CountryMapper;
import com.trebol.travelstats.repositories.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    @Cacheable("countries")
    public List<CountryDTO> getAllCountries() {
        return countryRepository.findAll()
                                .stream()
                                .map(countryMapper::map)
                                .toList();
    }
}