package com.trebol.travelstats.controllers;

import com.trebol.travelstats.services.StatisticsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class StatisticsControllerTest {

    private static final String STATS_BY_CARRIER_EXPECTED = "[\n" +
            "{\"carrier\":\"Aeroflot\", \"flights\": 4, \"distance\":17670,\"average\":4418},\n" +
            "{\"carrier\":\"Air Asia\", \"flights\": 1, \"distance\":1318,\"average\":1318}\n" +
            "]";

    private static final String STATS_BY_YEAR_EXPECTED = "[\n" +
            "{\"year\": 1996, \"flights\": 2, \"distance\":482},\n" +
            "{\"year\": 2000, \"flights\": 2, \"distance\": 2382}\n" +
            "]";

    @Mock
    private StatisticsService statisticsService;

    private StatisticsController statisticsController;

    @Before
    public void setUp() throws Exception {
        statisticsController = new StatisticsController(statisticsService);
        when(statisticsService.getFlightsByCarrier()).thenReturn(STATS_BY_CARRIER_EXPECTED);
    }

    @Test
    public void flightsByCarrier() throws Exception {
        // given
        when(statisticsService.getFlightsByCarrier()).thenReturn(STATS_BY_CARRIER_EXPECTED);

        //when then
        assertEquals(STATS_BY_CARRIER_EXPECTED, statisticsController.getFlightsByCarrier());
    }

    @Test
    public void flightsByYear() throws Exception {
        // given
        when(statisticsService.getFlightsByYear()).thenReturn(STATS_BY_YEAR_EXPECTED);

        //when then
        assertEquals(STATS_BY_YEAR_EXPECTED, statisticsController.getFlightsByYear());
    }
}