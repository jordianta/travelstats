package com.trebol.travelstats.controllers;


import com.trebol.travelstats.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    public FlightController(final FlightService flightService) {
        this.flightService = flightService;
    }


    @RequestMapping
    public String getAllFlights() {
        return flightService.getAllFlights();
    }
}
