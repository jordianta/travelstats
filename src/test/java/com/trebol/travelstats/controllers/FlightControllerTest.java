package com.trebol.travelstats.controllers;

import com.trebol.travelstats.controllers.rest.FlightController;
import com.trebol.travelstats.datatransferobjects.FlightDTO;
import com.trebol.travelstats.services.FlightService;
import com.trebol.travelstats.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.trebol.travelstats.utils.TestUtils.createFlightDTOList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class FlightControllerTest {

    private static final List<FlightDTO> FLIGHTS_EXPECTED = createFlightDTOList();

    @Mock
    private FlightService flightService;

    private FlightController flightController;

    @BeforeEach
    void setUp() {
        flightController = new FlightController(flightService);
    }

    @Test
    void getAllFlights() {
        // given
        when(flightService.getAllFlights()).thenReturn(FLIGHTS_EXPECTED);

        // when then
        assertEquals(FLIGHTS_EXPECTED, flightController.getAllFlights());
    }

    @Test
    void addFlight() {
        // given
        final var flightDTO = TestUtils.createBCNToJFKFlightDTO();

        // when
        flightController.addFlight(flightDTO);

        // then
        verify(flightService).createFlight(flightDTO);
    }

    @Test
    void removeFlight() {
        // given
        final Long flightId = 1L;

        // when
        flightController.deleteFlight(flightId);

        // then
        verify(flightService).deleteFlight(flightId);
    }

}