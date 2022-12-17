package com.trebol.travelstats.services;

import com.trebol.travelstats.datatransferobjects.FlightDTO;
import com.trebol.travelstats.mappers.FlightMapper;
import com.trebol.travelstats.repositories.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    @Override
    public List<FlightDTO> getAllFlights() {
        return flightRepository.findAllByOrderByDateAsc()
                               .stream()
                               .map(flightMapper::map)
                               .toList();
    }

    @Override
    public void createFlight(final FlightDTO flightDTO) {
        final var flight = flightMapper.map(flightDTO);
        flightRepository.save(flight);
    }

    @Override
    public void deleteFlight(final Long flightId) {
        flightRepository.deleteById(flightId);
    }
}
