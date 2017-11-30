package com.trebol.travelstats.controllers;

import com.trebol.travelstats.services.FlightService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class FlightControllerTest {

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

    @Mock
    private FlightService flightService;

    private FlightController flightController;

    @Before
    public void setUp() throws Exception {
        flightController = new FlightController(flightService);
    }

    @Test
    public void getAllFlights() throws Exception {
        // given
        when(flightService.getAllFlights()).thenReturn(FLIGHTS_EXPECTED);

        // when then
        assertEquals(FLIGHTS_EXPECTED, flightController.getAllFlights());
    }

}