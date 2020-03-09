package com.trebol.travelstats.controllers;

import com.trebol.travelstats.datatransferobjects.FlightDTO;
import com.trebol.travelstats.services.FlightService;
import com.trebol.travelstats.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class FlightControllerTest {

    private static final List<FlightDTO> FLIGHTS_EXPECTED = TestUtils.createFlightDTOList();

    @Mock
    private FlightService flightService;

    private FlightController flightController;

    @Before
    public void setUp() {
        flightController = new FlightController(flightService);
    }

    @Test
    public void getAllFlights() {
        // given
        when(flightService.getAllFlights()).thenReturn(FLIGHTS_EXPECTED);

        // when then
        assertEquals(FLIGHTS_EXPECTED, flightController.getAllFlights());
    }

    @Test
    public void addFlight() {
        // given
        final var flightDTO = TestUtils.createBCNToJFKFlightDTO();

        // when
        flightController.addFlight(flightDTO);

        // then
        verify(flightService, times(1)).createFlight(flightDTO);
    }

    @Test
    public void removeFlight() {
        // given
        final Long flightId = 1L;

        // when
        flightController.deleteFlight(flightId);

        // then
        verify(flightService, times(1)).deleteFlight(flightId);
    }

}