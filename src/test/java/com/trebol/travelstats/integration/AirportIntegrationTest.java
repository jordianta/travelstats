package com.trebol.travelstats.integration;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.hamcrest.Matchers.equalTo;


class AirportIntegrationTest extends AbstractIntegrationTest {

    private static final String AIRPORTS_EXPECTED = "[" +
                                                    "{\"id\":578,\"name\":\"El Prat\",\"latitude\":41.3,\"longitude\":2.083333,\"city\":\"Barcelona\",\"iataCode\":\"BCN\",\"country\":{\"id\":69,\"name\":\"Spain\",\"continentId\":1,\"isoCode\":\"ESP\"}}," +
                                                    "{\"id\":3408,\"name\":\"John F Kennedy Intl Airport\",\"latitude\":40.63861,\"longitude\":-73.76222,\"city\":\"New York , NY\",\"iataCode\":\"JFK\",\"country\":{\"id\":229,\"name\":\"United States\",\"continentId\":3,\"isoCode\":\"USA\"}}" +
                                                    "]";


    @Override
    protected String getBasePath() {
        return "api/airports";
    }

    @Test
    void airports() {
        createGiven()

                .when()
                .get()

                .then()
                .statusCode(HttpStatus.OK.value())
                .body(equalTo(AIRPORTS_EXPECTED));
    }
}
