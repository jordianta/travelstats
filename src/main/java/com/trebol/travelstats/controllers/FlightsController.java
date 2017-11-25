package com.trebol.travelstats.controllers;


import com.trebol.travelstats.services.FlightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flights")
public class FlightsController {

    @Autowired
    private FlightsService flightsService;

    public FlightsController(final FlightsService flightsService) {
        this.flightsService = flightsService;
    }


    @RequestMapping
    public String getAllFlights() {
        return flightsService.getAllFlights();
    }
}
