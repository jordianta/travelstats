package com.trebol.travelstats.services;

import com.trebol.travelstats.datatransferobjects.FlightDTO;
import com.trebol.travelstats.domainobjects.Flight;
import com.trebol.travelstats.mappers.FlightMapper;
import com.trebol.travelstats.repositories.FlightRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class FlightServiceImpl implements FlightService
{

    private FlightRepository flightRepository;
    private FlightMapper flightMapper;


    public FlightServiceImpl(final FlightRepository flightRepository, final FlightMapper flightMapper)
    {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
    }


    @Override
    public List<FlightDTO> getAllFlights()
    {
        return flightRepository.findAll()
            .stream()
            .map(flight -> flightMapper.map(flight, FlightDTO.class))
            .collect(Collectors.toList());
    }


    @Override
    public void createFlight(final FlightDTO flightDTO)
    {
        final Flight flight = flightMapper.map(flightDTO, Flight.class);
        flightRepository.save(flight);
    }


    @Override
    public void deleteFlight(final Long flightId)
    {
        flightRepository.delete(flightId);
    }
}
