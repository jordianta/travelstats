package com.trebol.travelstats.services;

import com.trebol.travelstats.datatransferobjects.AirportDTO;
import com.trebol.travelstats.mappers.AirportMapper;
import com.trebol.travelstats.repositories.AirportRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@Service
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;

    @Override
    @Cacheable("airports")
    public List<AirportDTO> getAllAirports() {
        return airportRepository.findAll()
                                .stream()
                                .map(airport -> airportMapper.map(airport, AirportDTO.class))
                                .collect(toList());
    }
}