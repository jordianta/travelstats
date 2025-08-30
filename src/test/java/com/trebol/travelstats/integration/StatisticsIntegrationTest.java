package com.trebol.travelstats.integration;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.hamcrest.Matchers.equalTo;


class StatisticsIntegrationTest extends AbstractIntegrationTest {

    private static final String STATS_BY_CARRIER_EXPECTED = "[" +
                                                            "{\"carrier\":\"American Airlines\",\"flights\":1,\"distance\":7000,\"average\":7000,\"time\":8.0,\"averageTime\":8.0}," +
                                                            "{\"carrier\":\"Qantas Airways\",\"flights\":1,\"distance\":7100,\"average\":7100,\"time\":8.5,\"averageTime\":8.5}" +
                                                            "]";

    private static final String STATS_BY_YEAR_EXPECTED = "[{\"year\":1996,\"flights\":2,\"distance\":14100,\"time\":16.5,\"averageTime\":8.25}]";


    @Override
    protected String getBasePath() {
        return "api/stats";
    }

    @Test
    void flightsByCarrier() {
        createGiven()

                .when()
                .get("/flights/carrier")

                .then()
                .statusCode(HttpStatus.OK.value())
                .body(equalTo(STATS_BY_CARRIER_EXPECTED));
    }

    @Test
    void flightsByYear() {
        createGiven()

                .when()
                .get("/flights/year")

                .then()
                .statusCode(HttpStatus.OK.value())
                .body(equalTo(STATS_BY_YEAR_EXPECTED));
    }
}
