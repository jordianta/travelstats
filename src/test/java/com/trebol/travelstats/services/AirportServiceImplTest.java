package com.trebol.travelstats.services;

import com.trebol.travelstats.datatransferobjects.AirportDTO;
import com.trebol.travelstats.domainobjects.Airport;
import com.trebol.travelstats.mappers.AirportMapper;
import com.trebol.travelstats.mappers.AirportMapperImpl;
import com.trebol.travelstats.mappers.CountryMapperImpl;
import com.trebol.travelstats.repositories.AirportRepository;
import com.trebol.travelstats.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AirportMapperImpl.class, CountryMapperImpl.class})
class AirportServiceImplTest {

    private static final List<Airport> AIRPORTS_FROM_DB = TestUtils.createAirportList();
    private static final List<AirportDTO> AIRPORTS_EXPECTED = TestUtils.createAirportDTOList();

    @Mock
    private AirportRepository airportRepository;

    @Autowired
    private AirportMapper airportMapper;

    private AirportService airportService;

    @BeforeEach
    void setUp() {
        airportService = new AirportServiceImpl(airportRepository, airportMapper);
    }

    @Test
    void getAllAirports() {
        // given
        when(airportRepository.findAll()).thenReturn(AIRPORTS_FROM_DB);

        // when
        final var allAirports = airportService.getAllAirports();

        // then
        assertThat(allAirports, equalTo(AIRPORTS_EXPECTED));
    }

    @Test
    void getAllAirports_WithEmptyList() {
        // given
        when(airportRepository.findAll()).thenReturn(Collections.emptyList());

        // when
        final var allAirports = airportService.getAllAirports();

        // then
        assertThat(allAirports, hasSize(0));
    }

}