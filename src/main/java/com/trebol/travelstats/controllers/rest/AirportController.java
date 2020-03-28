package com.trebol.travelstats.controllers.rest;

import com.trebol.travelstats.datatransferobjects.AirportDTO;
import com.trebol.travelstats.services.AirportService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/airports")
public class AirportController {

    private final AirportService airportService;

    @GetMapping
    public List<AirportDTO> getAllAirports() {
        return airportService.getAllAirports();
    }
}