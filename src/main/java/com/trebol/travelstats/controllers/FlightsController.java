package com.trebol.travelstats.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/flights")
public class FlightsController {

    private static final Logger LOG = LoggerFactory.getLogger(FlightsController.class);
    private static final String FLIGHTS_FILE_NAME = "json/flights.json";

    @RequestMapping
    public String getAllFlights() {
        return loadFileAsString(FLIGHTS_FILE_NAME);
    }

    private String loadFileAsString(final String fileName) {
        try {
            final URL resource = getResource(fileName);
            try (BufferedReader buffer = new BufferedReader(new InputStreamReader(resource.openStream()))) {
                return buffer.lines().collect(Collectors.joining("\n"));
            }

        } catch (IOException e) {
            LOG.error("Error reading {}", fileName, e);
        }
        return "";
    }

    private URL getResource(final String fileName) {
        return getClass().getClassLoader().getResource(fileName);
    }
}
