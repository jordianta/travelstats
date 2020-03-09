package com.trebol.travelstats.controllers;


import com.trebol.travelstats.controllers.responses.EmptyJsonResponse;
import com.trebol.travelstats.datatransferobjects.FlightDTO;
import com.trebol.travelstats.services.FlightService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("flights")
public class FlightController {

    private final FlightService flightService;

    @GetMapping
    public List<FlightDTO> getAllFlights() {
        return flightService.getAllFlights();
    }

    @PostMapping
    public void addFlight(@RequestBody final FlightDTO flightDTO) {
        flightService.createFlight(flightDTO);
    }

    @DeleteMapping("/{flightId}")
    public void deleteFlight(@PathVariable final Long flightId) {
        flightService.deleteFlight(flightId);
    }
}
