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

    private ArgumentCaptor<Flight> createArgumentCaptor = ArgumentCaptor.forClass(Flight.class);
    private ArgumentCaptor<Long> deleteArgumentCaptor = ArgumentCaptor.forClass(Long.class);

    @Mock
    private FlightRepository flightRepository;

    private FlightService flightService;

    @Before
    public void setUp() throws Exception {
        flightService = new FlightServiceImpl(flightRepository, new FlightMapper());
    }

    @Test
    public void getAllFlights() throws Exception {
        // given
        when(flightRepository.findAll()).thenReturn(FLIGHTS_FROM_DB);

        // when
        final List<FlightDTO> allFlights = flightService.getAllFlights();

        // then
        assertThat(allFlights, is(FLIGHTS_EXPECTED));
    }

    @Test
    public void getAllFlights_WithEmptyList() throws Exception {
        // given
        when(flightRepository.findAll()).thenReturn(Collections.emptyList());

        // when
        final List<FlightDTO> allFlights = flightService.getAllFlights();

        // then
        assertThat(allFlights, hasSize(0));
    }

    @Test
    public void createFlight() throws Exception {
        // given
        final FlightDTO flightDTO = TestUtils.createBCNToJFKFlightDTO();

        // when
        flightService.createFlight(flightDTO);

        // then
        verify(flightRepository, times(1)).save(createArgumentCaptor.capture());
        final Flight flightStored = createArgumentCaptor.getValue();
        assertEquals(flightDTO.getNumber(), flightStored.getNumber());
        assertEquals(flightDTO.getDistance(), flightStored.getDistance());
        assertEquals(flightDTO.getCarrier().getIataCode(), flightStored.getCarrier().getIataCode());
        assertEquals(flightDTO.getOrigin().getIataCode(), flightStored.getOrigin().getIataCode());
        assertEquals(flightDTO.getDestination().getIataCode(), flightStored.getDestination().getIataCode());
    }

    @Test
    public void deleteFlight() throws Exception {
        // given
        final Long flightId = 1L;

        // when
        flightService.deleteFlight(flightId);

        // then
        verify(flightRepository, times(1)).delete(deleteArgumentCaptor.capture());
        final Long flightIdRemoved = deleteArgumentCaptor.getValue();
        assertEquals(flightId, flightIdRemoved);
    }

}