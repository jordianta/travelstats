package com.trebol.travelstats.integration;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.hamcrest.Matchers.equalTo;

class CountryIntegrationTest extends AbstractIntegrationTest {

    private static final String COUNTRIES_EXPECTED = "[" +
                                                     "{\"id\":69,\"name\":\"Spain\",\"continentId\":1,\"isoCode\":\"ESP\"}," +
                                                     "{\"id\":229,\"name\":\"United States\",\"continentId\":3,\"isoCode\":\"USA\"}" +
                                                     "]";


    @Override
    protected String getBasePath() {
        return "api/countries";
    }

    @Test
    void countries() {
        createGiven()

                .when()
                .get()

                .then()
                .statusCode(HttpStatus.OK.value())
                .body(equalTo(COUNTRIES_EXPECTED));
    }
}
