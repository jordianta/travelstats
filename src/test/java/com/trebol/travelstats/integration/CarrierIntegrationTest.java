package com.trebol.travelstats.integration;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.hamcrest.Matchers.equalTo;

class CarrierIntegrationTest extends AbstractIntegrationTest {

    private static final String CARRIERS_EXPECTED = "[" +
                                                    "{\"id\":209,\"name\":\"American Airlines\",\"iataCode\":\"AA\"}," +
                                                    "{\"id\":845,\"name\":\"Qantas Airways\",\"iataCode\":\"QF\"}" +
                                                    "]";


    @Override
    protected String getBasePath() {
        return "api/carriers";
    }

    @Test
    void carriers() {
        createGiven()

                .when()
                .get()

                .then()
                .statusCode(HttpStatus.OK.value())
                .body(equalTo(CARRIERS_EXPECTED));
    }
}
