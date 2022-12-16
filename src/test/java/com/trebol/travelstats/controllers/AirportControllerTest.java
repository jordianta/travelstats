package com.trebol.travelstats.controllers;

import com.trebol.travelstats.controllers.rest.AirportController;
import com.trebol.travelstats.datatransferobjects.AirportDTO;
import com.trebol.travelstats.services.AirportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.trebol.travelstats.utils.TestUtils.createAirportDTOList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AirportControllerTest {

    private static final List<AirportDTO> ALL_AIRPORTS = createAirportDTOList();

    @Mock
    private AirportService airportService;

    private AirportController airportController;

    @BeforeEach
    void setUp() {
        airportController = new AirportController(airportService);
    }

    @Test
    void getAllAirports() {
        // given
        when(airportService.getAllAirports()).thenReturn(ALL_AIRPORTS);

        // when
        final var allAirports = airportController.getAllAirports();

        // then
        assertThat(allAirports, equalTo(ALL_AIRPORTS));
    }
}