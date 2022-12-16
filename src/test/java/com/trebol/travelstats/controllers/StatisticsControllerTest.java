package com.trebol.travelstats.controllers;

import com.trebol.travelstats.controllers.rest.StatisticsController;
import com.trebol.travelstats.datatransferobjects.StatsByCarrierDTO;
import com.trebol.travelstats.datatransferobjects.StatsByYearDTO;
import com.trebol.travelstats.services.StatisticsService;
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
class StatisticsControllerTest {

    @Mock
    private StatisticsService statisticsService;

    private StatisticsController statisticsController;


    @BeforeEach
    void setUp() {
        statisticsController = new StatisticsController(statisticsService);
    }


    @Test
    void flightsByCarrier() {
        // given
        when(statisticsService.getFlightsByCarrier()).thenReturn(createStatsByCarrierDTOList());

        // when
        final var statsByCarrierDTOList = statisticsController.getFlightsByCarrier();

        // then
        assertEquals("Qantas Airways", statsByCarrierDTOList.get(0).carrier());
        assertEquals(10000, statsByCarrierDTOList.get(0).distance());
        assertEquals(4, statsByCarrierDTOList.get(0).flights());
        assertEquals(2500, statsByCarrierDTOList.get(0).average());

        assertEquals("American Airlines", statsByCarrierDTOList.get(1).carrier());
        assertEquals(64000, statsByCarrierDTOList.get(1).distance());
        assertEquals(8, statsByCarrierDTOList.get(1).flights());
        assertEquals(8000, statsByCarrierDTOList.get(1).average());
    }


    @Test
    void flightsByYear() {
        // given
        when(statisticsService.getFlightsByYear()).thenReturn(createStatsByYearDTOList());

        //when then
        final var statsByYearDTOList = statisticsController.getFlightsByYear();

        // then
        // then
        assertEquals(1996, statsByYearDTOList.get(0).year());
        assertEquals(10000, statsByYearDTOList.get(0).distance());
        assertEquals(4, statsByYearDTOList.get(0).flights());

        assertEquals(1997, statsByYearDTOList.get(1).year());
        assertEquals(64000, statsByYearDTOList.get(1).distance());
        assertEquals(8, statsByYearDTOList.get(1).flights());
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