package com.trebol.travelstats.services;

import com.google.gson.Gson;
import com.trebol.travelstats.domainobjects.Airport;
import com.trebol.travelstats.repositories.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private Gson gson;

    public AirportServiceImpl(final AirportRepository airportRepository, final Gson gson) {
        this.airportRepository = airportRepository;
        this.gson = gson;
    }

    @Override
    public String getAllAirports() {
        final List<Airport> airports = airportRepository.findAll();

        if (airports == null) {
            return gson.toJson(Collections.emptyList());
        }
        return gson.toJson(airports);
    }
}