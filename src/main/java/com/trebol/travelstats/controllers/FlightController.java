package com.trebol.travelstats.controllers;


import com.trebol.travelstats.datatransferobjects.FlightDTO;
import com.trebol.travelstats.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    public FlightController(final FlightService flightService) {
        this.flightService = flightService;
    }


    @RequestMapping
    public List<FlightDTO> getAllFlights() {
        return flightService.getAllFlights();
    }
}
