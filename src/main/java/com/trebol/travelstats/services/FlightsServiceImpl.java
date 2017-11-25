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
public class FlightsServiceImpl implements FlightsService {

    private static final Logger LOG = LoggerFactory.getLogger(FlightsServiceImpl.class);
    private static final String FLIGHTS_FILE_NAME = "json/flights.json";

    public FlightsServiceImpl() {
    }

    @Override
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
