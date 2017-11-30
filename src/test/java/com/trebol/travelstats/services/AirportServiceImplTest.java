package com.trebol.travelstats.services;

import com.google.gson.Gson;
import com.trebol.travelstats.domainobjects.Airport;
import com.trebol.travelstats.repositories.AirportRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AirportServiceImplTest {

    private static final List<Airport> AIRPORTS_LIST = createAirportList();

    @Mock
    private AirportRepository airportRepository;

    private Gson gson;
    private AirportService airportService;

    @Before
    public void setUp() throws Exception {
        gson = new Gson();
        airportService = new AirportServiceImpl(airportRepository, gson);
    }

    @Test
    public void getAllAirports() throws Exception {
        // given
        when(airportRepository.findAll()).thenReturn(AIRPORTS_LIST);

        // when
        final String allAirports = airportService.getAllAirports();

        // then
        assertThat(allAirports, equalTo(gson.toJson(AIRPORTS_LIST)));
    }

    @Test
    public void getAllAirports_WithEmptyList() throws Exception {
        // given
        when(airportRepository.findAll()).thenReturn(Collections.emptyList());

        // when
        final String allAirports = airportService.getAllAirports();

        // then
        assertThat(allAirports, equalTo(gson.toJson(Collections.emptyList())));
    }

    @Test
    public void getAllAirports_WithNullList() throws Exception {
        // given
        when(airportRepository.findAll()).thenReturn(null);

        // when
        final String allAirports = airportService.getAllAirports();

        // then
        assertThat(allAirports, equalTo(gson.toJson(Collections.emptyList())));
    }

    private static List<Airport> createAirportList() {

        final Airport airportBCN = new Airport();
        airportBCN.setCity("Barcelona");
        airportBCN.setCountryId(69);
        airportBCN.setIataCode("BCN");
        airportBCN.setLatitude(41.3F);
        airportBCN.setLongitude(2.083333F);
        airportBCN.setId(1);
        airportBCN.setName("El Prat");

        final Airport airportJFK = new Airport();
        airportJFK.setCity("New York");
        airportJFK.setCountryId(229);
        airportJFK.setIataCode("JFK");
        airportJFK.setLatitude(40.638611F);
        airportJFK.setLongitude(-73.762222F);
        airportJFK.setId(2);
        airportJFK.setName("John F Kennedy Intl Airport");

        return Arrays.asList(airportBCN, airportJFK);
    }

}