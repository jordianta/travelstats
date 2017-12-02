package com.trebol.travelstats.controllers;

import com.trebol.travelstats.datatransferobjects.AirportDTO;
import com.trebol.travelstats.services.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/airports")
public class AirportController {

    @Autowired
    private AirportService airportService;

    public AirportController(final AirportService airportService) {
        this.airportService = airportService;
    }

    @RequestMapping("")
    public List<AirportDTO> getAllAirports() {
        return airportService.getAllAirports();
    }
}