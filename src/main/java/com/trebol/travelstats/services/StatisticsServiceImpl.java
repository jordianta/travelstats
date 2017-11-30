package com.trebol.travelstats.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    private static final Logger LOG = LoggerFactory.getLogger(StatisticsServiceImpl.class);

    private static final String FLIGHTS_STATS_BY_CARRIER_FILE_NAME = "json/flightsStatsByCarrier.json";
    private static final String FLIGHTS_STATS_BY_YEAR_FILE_NAME = "json/flightsStatsByYear.json";

    @Override
    public String getFlightsByCarrier() {
        return loadFileAsString(FLIGHTS_STATS_BY_CARRIER_FILE_NAME);
    }

    @Override
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