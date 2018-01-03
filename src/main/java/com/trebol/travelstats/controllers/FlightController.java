package com.trebol.travelstats.controllers;


import com.trebol.travelstats.controllers.responses.EmptyJsonResponse;
import com.trebol.travelstats.datatransferobjects.FlightDTO;
import com.trebol.travelstats.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("flights")
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

    @PostMapping
    public ResponseEntity<EmptyJsonResponse> addFlight(@RequestBody final FlightDTO flightDTO) {
        flightService.createFlight(flightDTO);
        return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.OK);
    }

    @DeleteMapping("/{flightId}")
    public ResponseEntity<EmptyJsonResponse> deleteFlight(@PathVariable final Long flightId) {
        flightService.deleteFlight(flightId);
        return new ResponseEntity<>(new EmptyJsonResponse(), HttpStatus.OK);
    }
}
