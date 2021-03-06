package com.trebol.travelstats.services;

import com.trebol.travelstats.datatransferobjects.FlightDTO;
import com.trebol.travelstats.domainobjects.Flight;
import com.trebol.travelstats.mappers.FlightMapper;
import com.trebol.travelstats.repositories.FlightRepository;
import com.trebol.travelstats.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class FlightServiceImplTest {

    private static final List<Flight> FLIGHTS_FROM_DB = TestUtils.createFlightList();
    private static final List<FlightDTO> FLIGHTS_EXPECTED = TestUtils.createFlightDTOList();

    private final ArgumentCaptor<Flight> createArgumentCaptor = ArgumentCaptor.forClass(Flight.class);
    private final ArgumentCaptor<Long> deleteArgumentCaptor = ArgumentCaptor.forClass(Long.class);

    @Mock
    private FlightRepository flightRepository;

    private FlightService flightService;

    @Before
    public void setUp() {
        flightService = new FlightServiceImpl(flightRepository, new FlightMapper());
    }

    @Test
    public void getAllFlights() {
        // given
        when(flightRepository.findAllByOrderByDateAsc()).thenReturn(FLIGHTS_FROM_DB);

        // when
        final var allFlights = flightService.getAllFlights();

        // then
        assertThat(allFlights, is(FLIGHTS_EXPECTED));
    }

    @Test
    public void getAllFlights_WithEmptyList() {
        // given
        when(flightRepository.findAllByOrderByDateAsc()).thenReturn(Collections.emptyList());

        // when
        final var allFlights = flightService.getAllFlights();

        // then
        assertThat(allFlights, hasSize(0));
    }

    @Test
    public void createFlight() {
        // given
        final var flightDTO = TestUtils.createBCNToJFKFlightDTO();

        // when
        flightService.createFlight(flightDTO);

        // then
        verify(flightRepository, times(1)).save(createArgumentCaptor.capture());
        final var flightStored = createArgumentCaptor.getValue();
        assertEquals(flightDTO.getNumber(), flightStored.getNumber());
        assertEquals(flightDTO.getDistance(), flightStored.getDistance());
        assertEquals(flightDTO.getCarrier().getIataCode(), flightStored.getCarrier().getIataCode());
        assertEquals(flightDTO.getOrigin().getIataCode(), flightStored.getOrigin().getIataCode());
        assertEquals(flightDTO.getDestination().getIataCode(), flightStored.getDestination().getIataCode());
    }

    @Test
    public void deleteFlight() {
        // given
        final Long flightId = 1L;

        // when
        flightService.deleteFlight(flightId);

        // then
        verify(flightRepository, times(1)).deleteById(deleteArgumentCaptor.capture());
        final var flightIdRemoved = deleteArgumentCaptor.getValue();
        assertEquals(flightId, flightIdRemoved);
    }

}