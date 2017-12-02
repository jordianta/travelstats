package com.trebol.travelstats.controllers;

import com.trebol.travelstats.datatransferobjects.AirportDTO;
import com.trebol.travelstats.datatransferobjects.CountryDTO;
import com.trebol.travelstats.services.AirportService;
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

    private static final List<AirportDTO> AIRPORTSDTO_LIST = createAirportDTOList();

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
        when(airportService.getAllAirports()).thenReturn(AIRPORTSDTO_LIST);

        // when
        final List<AirportDTO> allAirports = airportController.getAllAirports();

        // then
        assertThat(allAirports, equalTo(AIRPORTSDTO_LIST));
    }

    private static List<AirportDTO> createAirportDTOList() {

        final CountryDTO spain = new CountryDTO(69, "Spain", 1, "ESP");
        final AirportDTO airportBCN = new AirportDTO(577, "El Prat", 41.3F, 2.083333F, "Barcelona", "BCN", spain);

        final CountryDTO usa = new CountryDTO(229, "United States", 3, "USA");
        final AirportDTO airportJFK = new AirportDTO(3407, "John F Kennedy Intl Airport", 40.638611F, -73.762222F, "New York", "JFK", usa);

        return Arrays.asList(airportBCN, airportJFK);
    }


}