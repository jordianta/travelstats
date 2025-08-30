package com.trebol.travelstats.services;

import com.trebol.travelstats.domainobjects.Flight;
import com.trebol.travelstats.repositories.FlightRepository;
import com.trebol.travelstats.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class StatisticsServiceImplTest {

    private StatisticsService statisticsService;

    @Mock
    private FlightRepository flightRepository;


    @BeforeEach
    void setUp() {
        statisticsService = new StatisticsServiceImpl(flightRepository);
    }


    @Test
    void getFlightsByCarrier() {
        // given
        final var flightList1 = TestUtils.createFlightList();
        flightList1.forEach(flight -> flight.setCarrier(TestUtils.createQantasCarrier()));
        flightList1.forEach(flight -> flight.setDistance(5000));
        final var flightList2 = TestUtils.createFlightList();
        flightList2.forEach(flight -> flight.setCarrier(TestUtils.createAACarrier()));
        flightList2.forEach(flight -> flight.setDistance(6000));

        final List<Flight> flightList = new ArrayList<>(flightList1);
        flightList.addAll(flightList2);

        when(flightRepository.findAll()).thenReturn(flightList);

        // when
        final var statsByCarrierDTOList = statisticsService.getFlightsByCarrier();

        // then
        assertEquals("American Airlines", statsByCarrierDTOList.get(0).carrier());
        assertEquals(12000, statsByCarrierDTOList.get(0).distance());
        assertEquals(2, statsByCarrierDTOList.get(0).flights());
        assertEquals(6000, statsByCarrierDTOList.get(0).average());

        assertEquals("Qantas Airways", statsByCarrierDTOList.get(1).carrier());
        assertEquals(10000, statsByCarrierDTOList.get(1).distance());
        assertEquals(2, statsByCarrierDTOList.get(1).flights());
        assertEquals(5000, statsByCarrierDTOList.get(1).average());

    }


    @Test
    void getFlightsByYear() {
        // given
        final var flightList1 = TestUtils.createFlightList();
        final var flightList2 = TestUtils.createFlightList();
        flightList2.forEach(flight -> flight.setDate(flight.getDate().withYear(1997)));


        final List<Flight> flightList = new ArrayList<>(flightList1);
        flightList.addAll(flightList2);

        when(flightRepository.findAll()).thenReturn(flightList);

        // when
        final var statsByYearDTOList = statisticsService.getFlightsByYear();

        // then
        assertEquals(1996, statsByYearDTOList.get(0).year());
        assertEquals(14100, statsByYearDTOList.get(0).distance());
        assertEquals(2, statsByYearDTOList.get(0).flights());

        assertEquals(1997, statsByYearDTOList.get(1).year());
        assertEquals(14100, statsByYearDTOList.get(1).distance());
        assertEquals(2, statsByYearDTOList.get(1).flights());
    }

}