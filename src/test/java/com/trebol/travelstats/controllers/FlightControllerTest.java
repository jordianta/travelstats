package com.trebol.travelstats.controllers;

import com.trebol.travelstats.datatransferobjects.AirportDTO;
import com.trebol.travelstats.datatransferobjects.CarrierDTO;
import com.trebol.travelstats.datatransferobjects.CountryDTO;
import com.trebol.travelstats.datatransferobjects.FlightDTO;
import com.trebol.travelstats.services.FlightService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Time;
import java.util.Calendar;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class FlightControllerTest {

    private static final List<FlightDTO> FLIGHTS_EXPECTED = createFlightDTOList();

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

    private static List<FlightDTO> createFlightDTOList() {

        final CountryDTO spain = new CountryDTO(69, "Spain", 1, "ESP");
        final AirportDTO airportBCN = new AirportDTO(577, "El Prat", 41.3F, 2.083333F, "Barcelona", "BCN", spain);

        final CountryDTO usa = new CountryDTO(229, "United States", 3, "USA");
        final AirportDTO airportJFK = new AirportDTO(3407, "John F Kennedy Intl Airport", 40.638611F, -73.762222F, "New York", "JFK", usa);

        final CarrierDTO americanAirlines = new CarrierDTO(209, "American Airlines", "AA");
        final CarrierDTO qantas = new CarrierDTO(845, "Qantas Airways", "QF");

        final FlightDTO flight1 = new FlightDTO(1, airportBCN, airportJFK, americanAirlines, "15-08-1996", 7000, Time.valueOf("08:00:00"), "AA23");
        final FlightDTO flight2 = new FlightDTO(2, airportJFK, airportBCN, qantas, "23-08-1996", 7100, Time.valueOf("08:30:00"), "QF543");

        return asList(flight1, flight2);
    }

}