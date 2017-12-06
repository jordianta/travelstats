package com.trebol.travelstats.services;

import com.trebol.travelstats.datatransferobjects.FlightDTO;

import java.util.List;

public interface FlightService {

    List<FlightDTO> getAllFlights();

    void createFlight(FlightDTO flightDTO);

    void deleteFlight(Long flightId);
}
