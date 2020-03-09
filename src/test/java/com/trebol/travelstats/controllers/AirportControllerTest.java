package com.trebol.travelstats.controllers;

import com.trebol.travelstats.datatransferobjects.AirportDTO;
import com.trebol.travelstats.datatransferobjects.CountryDTO;
import com.trebol.travelstats.services.AirportService;
import com.trebol.travelstats.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AirportControllerTest {

    private static final List<AirportDTO> AIRPORTSDTO_LIST = TestUtils.createAirportDTOList();

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
        when(airportService.getAllAirports()).thenReturn(AIRPORTSDTO_LIST);

        // when
        final List<AirportDTO> allAirports = airportController.getAllAirports();

        // then
        assertThat(allAirports, equalTo(AIRPORTSDTO_LIST));
    }
}