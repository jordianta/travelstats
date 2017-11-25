package com.trebol.travelstats.services;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StatisticsServiceImplTest {

    private static final String STATS_BY_CARRIER_EXPECTED = "[\n" +
            "{\"carrier\":\"Aeroflot\", \"flights\": 4, \"distance\":17670,\"average\":4418},\n" +
            "{\"carrier\":\"Air Asia\", \"flights\": 1, \"distance\":1318,\"average\":1318}\n" +
            "]";

    private static final String STATS_BY_YEAR_EXPECTED = "[\n" +
            "{\"year\": 1996, \"flights\": 2, \"distance\":482},\n" +
            "{\"year\": 2000, \"flights\": 2, \"distance\": 2382}\n" +
            "]";

    private StatisticsService statisticsService;

    @Before
    public void setUp() throws Exception {
        statisticsService = new StatisticsServiceImpl();
    }

    @Test
    public void getFlightsByCarrier() throws Exception {
        assertEquals(STATS_BY_CARRIER_EXPECTED, statisticsService.getFlightsByCarrier());
    }

    @Test
    public void getFlightsByYear() throws Exception {
        assertEquals(STATS_BY_YEAR_EXPECTED, statisticsService.getFlightsByYear());
    }

}