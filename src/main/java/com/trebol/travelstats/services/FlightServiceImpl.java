package com.trebol.travelstats.services;

import com.trebol.travelstats.datatransferobjects.FlightDTO;
import com.trebol.travelstats.domainobjects.Flight;
import com.trebol.travelstats.mappers.FlightMapper;
import com.trebol.travelstats.repositories.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    @Override
    public List<FlightDTO> getAllFlights() {
        return flightRepository.findAllByOrderByDateAsc()
                               .stream()
                               .map(airport -> flightMapper.map(airport, FlightDTO.class))
                               .collect(toList());
    }

    @Override
    public void createFlight(final FlightDTO flightDTO) {
        final var flight = flightMapper.map(flightDTO, Flight.class);
        flightRepository.save(flight);
    }

    @Override
    public void deleteFlight(final Long flightId) {
        flightRepository.deleteById(flightId);
    }
}
