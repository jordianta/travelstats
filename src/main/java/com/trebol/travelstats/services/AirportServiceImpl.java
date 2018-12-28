package com.trebol.travelstats.services;

import com.trebol.travelstats.datatransferobjects.AirportDTO;
import com.trebol.travelstats.mappers.AirportMapper;
import com.trebol.travelstats.repositories.AirportRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class AirportServiceImpl implements AirportService
{

    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;


    public AirportServiceImpl(final AirportRepository airportRepository, final AirportMapper airportMapper)
    {
        this.airportRepository = airportRepository;
        this.airportMapper = airportMapper;
    }


    @Override
    @Cacheable("airports")
    public List<AirportDTO> getAllAirports()
    {
        return airportRepository.findAll()
            .stream()
            .map(airport -> airportMapper.map(airport, AirportDTO.class))
            .collect(Collectors.toList());
    }
}