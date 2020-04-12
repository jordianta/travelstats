package com.trebol.travelstats.controllers;

import com.trebol.travelstats.controllers.rest.AirportController;
import com.trebol.travelstats.datatransferobjects.AirportDTO;
import com.trebol.travelstats.services.AirportService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.trebol.travelstats.utils.TestUtils.createAirportDTOList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AirportControllerTest {

    private static final List<AirportDTO> ALL_AIRPORTS = createAirportDTOList();

    @Mock
    private AirportService airportService;

    private AirportController airportController;

    @Before
    public void setUp() {
        airportController = new AirportController(airportService);
    }

    @Test
    public void getAllAirports() {
        // given
        when(airportService.getAllAirports()).thenReturn(ALL_AIRPORTS);

        // when
        final var allAirports = airportController.getAllAirports();

        // then
        assertThat(allAirports, equalTo(ALL_AIRPORTS));
    }
}