package com.trebol.travelstats.controllers;

import com.trebol.travelstats.controllers.rest.StatisticsController;
import com.trebol.travelstats.datatransferobjects.StatsByCarrierDTO;
import com.trebol.travelstats.datatransferobjects.StatsByYearDTO;
import com.trebol.travelstats.services.StatisticsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class StatisticsControllerTest {

    @Mock
    private StatisticsService statisticsService;

    private StatisticsController statisticsController;


    @Before
    public void setUp() {
        statisticsController = new StatisticsController(statisticsService);
    }


    @Test
    public void flightsByCarrier() {
        // given
        when(statisticsService.getFlightsByCarrier()).thenReturn(createStatsByCarrierDTOList());

        // when
        final var statsByCarrierDTOList = statisticsController.getFlightsByCarrier();

        // then
        assertEquals("Qantas Airways", statsByCarrierDTOList.get(0).getCarrier());
        assertEquals(Integer.valueOf(10000), statsByCarrierDTOList.get(0).getDistance());
        assertEquals(Integer.valueOf(4), statsByCarrierDTOList.get(0).getFlights());
        assertEquals(Integer.valueOf(2500), statsByCarrierDTOList.get(0).getAverage());

        assertEquals("American Airlines", statsByCarrierDTOList.get(1).getCarrier());
        assertEquals(Integer.valueOf(64000), statsByCarrierDTOList.get(1).getDistance());
        assertEquals(Integer.valueOf(8), statsByCarrierDTOList.get(1).getFlights());
        assertEquals(Integer.valueOf(8000), statsByCarrierDTOList.get(1).getAverage());
    }


    @Test
    public void flightsByYear() {
        // given
        when(statisticsService.getFlightsByYear()).thenReturn(createStatsByYearDTOList());

        //when then
        final var statsByYearDTOList = statisticsController.getFlightsByYear();

        // then
        // then
        assertEquals(Integer.valueOf(1996), statsByYearDTOList.get(0).getYear());
        assertEquals(Integer.valueOf(10000), statsByYearDTOList.get(0).getDistance());
        assertEquals(Integer.valueOf(4), statsByYearDTOList.get(0).getFlights());

        assertEquals(Integer.valueOf(1997), statsByYearDTOList.get(1).getYear());
        assertEquals(Integer.valueOf(64000), statsByYearDTOList.get(1).getDistance());
        assertEquals(Integer.valueOf(8), statsByYearDTOList.get(1).getFlights());
    }


    private static List<StatsByCarrierDTO> createStatsByCarrierDTOList() {
        final List<StatsByCarrierDTO> statsByCarrierDTOList = new ArrayList<>();
        statsByCarrierDTOList.add(new StatsByCarrierDTO("Qantas Airways", 4, 10000, 2500, 12d, 3d));
        statsByCarrierDTOList.add(new StatsByCarrierDTO("American Airlines", 8, 64000, 8000, 32d, 4d));
        return statsByCarrierDTOList;
    }


    private static List<StatsByYearDTO> createStatsByYearDTOList() {
        final List<StatsByYearDTO> statsByYearDTOList = new ArrayList<>();
        statsByYearDTOList.add(new StatsByYearDTO(1996, 4, 10000, 6d, 1.5d));
        statsByYearDTOList.add(new StatsByYearDTO(1997, 8, 64000, 16d, 2d));
        return statsByYearDTOList;
    }
}