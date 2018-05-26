package com.trebol.travelstats.services;

import com.trebol.travelstats.datatransferobjects.CountryDTO;
import com.trebol.travelstats.mappers.CountryMapper;
import com.trebol.travelstats.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private CountryMapper countryMapper;

    public CountryServiceImpl(final CountryRepository countryRepository, final CountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }

    @Override
    @Cacheable("countries")
    public List<CountryDTO> getAllCountries() {
        return countryRepository.findAll()
                                .stream()
                                .map(airport -> countryMapper.map(airport, CountryDTO.class))
                                .collect(Collectors.toList());
    }
}