package com.trebol.travelstats.services;

import com.trebol.travelstats.datatransferobjects.AirportDTO;
import com.trebol.travelstats.mappers.AirportMapper;
import com.trebol.travelstats.repositories.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private AirportMapper airportMapper;

    public AirportServiceImpl(final AirportRepository airportRepository, final AirportMapper airportMapper) {
        this.airportRepository = airportRepository;
        this.airportMapper = airportMapper;
    }

    @Override
    public List<AirportDTO> getAllAirports() {
        return airportRepository.findAll()
                                .stream()
                                .map(airport -> airportMapper.map(airport, AirportDTO.class))
                                .collect(Collectors.toList());
    }
}