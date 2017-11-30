package com.trebol.travelstats.controllers;

import com.trebol.travelstats.services.AirportService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AirportControllerTest {

    private static final String ALL_AIRPORTS = "[" +
            "{\"id\":1,\"name\":\"El Prat\",\"latitude\":41.3,\"longitude\":2.083333,\"city\":\"Barcelona\",\"iataCode\":\"BCN\",\"countryId\":69}," +
            "{\"id\":2,\"name\":\"John F Kennedy Intl Airport\",\"latitude\":40.638611,\"longitude\":-73.762222,\"city\":\"New York\",\"iataCode\":\"JFK\",\"countryId\":229}" +
            "]";

    @Mock
    private AirportService airportService;

    private AirportController airportController;

    @Before
    public void setUp() throws Exception {
        airportController = new AirportController(airportService);
    }

    @Test
    public void getAllAirports() throws Exception {
        // given
        when(airportService.getAllAirports()).thenReturn(ALL_AIRPORTS);

        // when
        final String allAirports = airportController.getAllAirports();

        // then
        assertThat(allAirports, equalTo(ALL_AIRPORTS));
    }

}