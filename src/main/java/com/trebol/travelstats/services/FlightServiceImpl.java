package com.trebol.travelstats.services;

import com.trebol.travelstats.datatransferobjects.FlightDTO;
import com.trebol.travelstats.domainobjects.Flight;
import com.trebol.travelstats.mappers.FlightMapper;
import com.trebol.travelstats.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private FlightMapper flightMapper;

    public FlightServiceImpl(final FlightRepository flightRepository, final FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }

    @Override
    public List<FlightDTO> getAllFlights() {
        return flightRepository.findAll()
                               .stream()
                               .map(airport -> flightMapper.map(airport, FlightDTO.class))
                               .collect(Collectors.toList());
    }

    @Override
    public void createFlight(final FlightDTO flightDTO) {
        final Flight flight = flightMapper.map(flightDTO, Flight.class);
        flightRepository.save(flight);
    }

    @Override
    public void deleteFlight(final Long flightId) {
        flightRepository.delete(flightId);
    }
}
