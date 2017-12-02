package com.trebol.travelstats.services;

import com.trebol.travelstats.datatransferobjects.AirportDTO;
import com.trebol.travelstats.datatransferobjects.CountryDTO;
import com.trebol.travelstats.domainobjects.Airport;
import com.trebol.travelstats.domainobjects.Country;
import com.trebol.travelstats.mappers.AirportMapper;
import com.trebol.travelstats.repositories.AirportRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AirportServiceImplTest {

    private static final List<Airport> AIRPORTS_FROM_DB = createAirportList();
    private static final List<AirportDTO> AIRPORTS_EXPECTED = createAirportDTOList();

    @Mock
    private AirportRepository airportRepository;

    private AirportService airportService;

    @Before
    public void setUp() throws Exception {
        airportService = new AirportServiceImpl(airportRepository, new AirportMapper());
    }

    @Test
    public void getAllAirports() throws Exception {
        // given
        when(airportRepository.findAll()).thenReturn(AIRPORTS_FROM_DB);

        // when
        final List<AirportDTO> allAirports = airportService.getAllAirports();

        // then
        assertThat(allAirports, equalTo(AIRPORTS_EXPECTED));
    }

    @Test
    public void getAllAirports_WithEmptyList() throws Exception {
        // given
        when(airportRepository.findAll()).thenReturn(Collections.emptyList());

        // when
        final List<AirportDTO> allAirports = airportService.getAllAirports();

        // then
        assertThat(allAirports, hasSize(0));
    }

    private static List<Airport> createAirportList() {

        final Country spain = new Country();
        spain.setId(69);
        spain.setName("Spain");
        spain.setIsoCode("ESP");
        spain.setContinentId(1);

        final Airport airportBCN = new Airport();
        airportBCN.setCity("Barcelona");
        airportBCN.setCountry(spain);
        airportBCN.setIataCode("BCN");
        airportBCN.setLatitude(41.3F);
        airportBCN.setLongitude(2.083333F);
        airportBCN.setId(577);
        airportBCN.setName("El Prat");

        final Country usa = new Country();
        usa.setId(229);
        usa.setName("United States");
        usa.setIsoCode("USA");
        usa.setContinentId(3);

        final Airport airportJFK = new Airport();
        airportJFK.setCity("New York");
        airportJFK.setCountry(usa);
        airportJFK.setIataCode("JFK");
        airportJFK.setLatitude(40.638611F);
        airportJFK.setLongitude(-73.762222F);
        airportJFK.setId(3407);
        airportJFK.setName("John F Kennedy Intl Airport");

        return asList(airportBCN, airportJFK);
    }

    private static List<AirportDTO> createAirportDTOList() {

        final CountryDTO spain = new CountryDTO(69, "Spain", 1, "ESP");
        final AirportDTO airportBCN = new AirportDTO(577, "El Prat", 41.3F, 2.083333F, "Barcelona", "BCN", spain);

        final CountryDTO usa = new CountryDTO(229, "United States", 3, "USA");
        final AirportDTO airportJFK = new AirportDTO(3407, "John F Kennedy Intl Airport", 40.638611F, -73.762222F, "New York", "JFK", usa);

        return asList(airportBCN, airportJFK);
    }

}