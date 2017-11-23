package com.trebol.travelstats.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

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
            if (resource != null) {
                return new String(Files.readAllBytes(Paths.get(resource.toURI())));
            }

        } catch (URISyntaxException | IOException e) {
            LOG.error("Error reading {}", fileName, e);
        }
        return "";
    }

    private URL getResource(final String fileName) {
        return getClass().getClassLoader().getResource(fileName);
    }
}
