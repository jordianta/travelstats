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
@RequestMapping("/stats")
public class StatisticsController {

    private static final Logger LOG = LoggerFactory.getLogger(StatisticsController.class);
    private static final String FLIGHTS_STATS_BY_CARRIER_FILE_NAME = "json/flightsStatsByCarrier.json";
    private static final String FLIGHTS_STATS_BY_YEAR_FILE_NAME = "json/flightsStatsByYear.json";

    @RequestMapping("/flights/carrier/")
    public String getFlightsByCarrier() {
        return loadFileAsString(FLIGHTS_STATS_BY_CARRIER_FILE_NAME);
    }

    @RequestMapping("/flights/year/")
    public String getFlightsByYear() {
        return loadFileAsString(FLIGHTS_STATS_BY_YEAR_FILE_NAME);
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
