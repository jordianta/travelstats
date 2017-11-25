package com.trebol.travelstats.services;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FlightsServiceImplTest {

    private static final String FLIGHTS_EXPECTED = "[\n" +
            "    {\n" +
            "        \"distance\": 241,\n" +
            "        \"destination\": \"Menorca\",\n" +
            "        \"destinationCode\": 4487,\n" +
            "        \"destinationIata\": \"MAH\",\n" +
            "        \"origin\": \"Barcelona\",\n" +
            "        \"date\": \"15-8-1996\",\n" +
            "        \"originLng\": 2.083333,\n" +
            "        \"originCode\": 577,\n" +
            "        \"id\": 1,\n" +
            "        \"destinationLat\": 39.86667,\n" +
            "        \"carrierCode\": 566,\n" +
            "        \"carrier\": \"Iberia\",\n" +
            "        \"destinationLng\": 4.25,\n" +
            "        \"originIata\": \"BCN\",\n" +
            "        \"originLat\": 41.3\n" +
            "    },\n" +
            "    {\n" +
            "        \"distance\": 241,\n" +
            "        \"destination\": \"Barcelona\",\n" +
            "        \"destinationCode\": 577,\n" +
            "        \"destinationIata\": \"BCN\",\n" +
            "        \"origin\": \"Menorca\",\n" +
            "        \"date\": \"22-8-1996\",\n" +
            "        \"originLng\": 4.25,\n" +
            "        \"originCode\": 4487,\n" +
            "        \"id\": 2,\n" +
            "        \"destinationLat\": 41.3,\n" +
            "        \"carrierCode\": 566,\n" +
            "        \"carrier\": \"Iberia\",\n" +
            "        \"destinationLng\": 2.083333,\n" +
            "        \"originIata\": \"MAH\",\n" +
            "        \"originLat\": 39.86667\n" +
            "    }\n" +
            "]";

    private FlightsService flightsService;

    @Before
    public void setUp() throws Exception {
        flightsService = new FlightsServiceImpl();
    }

    @Test
    public void getAllFlights() throws Exception {
        assertEquals(FLIGHTS_EXPECTED, flightsService.getAllFlights());
    }

}