package com.trebol.travelstats.services;

import com.trebol.travelstats.datatransferobjects.AirportDTO;
import com.trebol.travelstats.domainobjects.Airport;
import com.trebol.travelstats.mappers.AirportMapper;
import com.trebol.travelstats.repositories.AirportRepository;
import com.trebol.travelstats.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AirportServiceImplTest {

    private static final List<Airport> AIRPORTS_FROM_DB = TestUtils.createAirportList();
    private static final List<AirportDTO> AIRPORTS_EXPECTED = TestUtils.createAirportDTOList();

    @Mock
    private AirportRepository airportRepository;

    private AirportService airportService;

    @BeforeEach
    void setUp() {
        airportService = new AirportServiceImpl(airportRepository, new AirportMapper());
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