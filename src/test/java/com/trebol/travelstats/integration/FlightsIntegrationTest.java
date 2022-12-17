package com.trebol.travelstats.integration;

import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.hamcrest.Matchers.equalTo;

class FlightsIntegrationTest extends AbstractIntegrationTest {

    private static final String FLIGHTS_EXPECTED = "[" +
                                                   "{\"id\":1,\"origin\":{\"id\":578,\"name\":\"El Prat\",\"latitude\":41.3,\"longitude\":2.083333,\"city\":\"Barcelona\",\"iataCode\":\"BCN\",\"country\":{\"id\":69,\"name\":\"Spain\",\"continentId\":1,\"isoCode\":\"ESP\"}},\"destination\":{\"id\":3408,\"name\":\"John F Kennedy Intl Airport\",\"latitude\":40.63861,\"longitude\":-73.76222,\"city\":\"New York , NY\",\"iataCode\":\"JFK\",\"country\":{\"id\":229,\"name\":\"United States\",\"continentId\":3,\"isoCode\":\"USA\"}},\"carrier\":{\"id\":209,\"name\":\"American Airlines\",\"iataCode\":\"AA\"},\"date\":\"15-08-1996\",\"distance\":7000,\"duration\":\"08:00:00\",\"number\":\"AA23\"}," +
                                                   "{\"id\":2,\"origin\":{\"id\":3408,\"name\":\"John F Kennedy Intl Airport\",\"latitude\":40.63861,\"longitude\":-73.76222,\"city\":\"New York , NY\",\"iataCode\":\"JFK\",\"country\":{\"id\":229,\"name\":\"United States\",\"continentId\":3,\"isoCode\":\"USA\"}},\"destination\":{\"id\":578,\"name\":\"El Prat\",\"latitude\":41.3,\"longitude\":2.083333,\"city\":\"Barcelona\",\"iataCode\":\"BCN\",\"country\":{\"id\":69,\"name\":\"Spain\",\"continentId\":1,\"isoCode\":\"ESP\"}},\"carrier\":{\"id\":845,\"name\":\"Qantas Airways\",\"iataCode\":\"QF\"},\"date\":\"23-08-1996\",\"distance\":7100,\"duration\":\"08:30:00\",\"number\":\"QF543\"}" +
                                                   "]";

    private static final String FLIGHT_TO_INSERT = "{\"origin\":{\"id\":578}, \"destination\":{\"id\":3408}, \"carrier\":{\"id\":209},\"date\":\"23-10-2006\",\"distance\":7000,\"duration\":\"08:15:00\",\"number\":\"AA25\"}";

    private static final String FLIGHTS_EXPECTED_AFTER_CREATION = "[" +
                                                   "{\"id\":1,\"origin\":{\"id\":578,\"name\":\"El Prat\",\"latitude\":41.3,\"longitude\":2.083333,\"city\":\"Barcelona\",\"iataCode\":\"BCN\",\"country\":{\"id\":69,\"name\":\"Spain\",\"continentId\":1,\"isoCode\":\"ESP\"}},\"destination\":{\"id\":3408,\"name\":\"John F Kennedy Intl Airport\",\"latitude\":40.63861,\"longitude\":-73.76222,\"city\":\"New York , NY\",\"iataCode\":\"JFK\",\"country\":{\"id\":229,\"name\":\"United States\",\"continentId\":3,\"isoCode\":\"USA\"}},\"carrier\":{\"id\":209,\"name\":\"American Airlines\",\"iataCode\":\"AA\"},\"date\":\"15-08-1996\",\"distance\":7000,\"duration\":\"08:00:00\",\"number\":\"AA23\"}," +
                                                   "{\"id\":2,\"origin\":{\"id\":3408,\"name\":\"John F Kennedy Intl Airport\",\"latitude\":40.63861,\"longitude\":-73.76222,\"city\":\"New York , NY\",\"iataCode\":\"JFK\",\"country\":{\"id\":229,\"name\":\"United States\",\"continentId\":3,\"isoCode\":\"USA\"}},\"destination\":{\"id\":578,\"name\":\"El Prat\",\"latitude\":41.3,\"longitude\":2.083333,\"city\":\"Barcelona\",\"iataCode\":\"BCN\",\"country\":{\"id\":69,\"name\":\"Spain\",\"continentId\":1,\"isoCode\":\"ESP\"}},\"carrier\":{\"id\":845,\"name\":\"Qantas Airways\",\"iataCode\":\"QF\"},\"date\":\"23-08-1996\",\"distance\":7100,\"duration\":\"08:30:00\",\"number\":\"QF543\"}," +
                                                   "{\"id\":3,\"origin\":{\"id\":578,\"name\":\"El Prat\",\"latitude\":41.3,\"longitude\":2.083333,\"city\":\"Barcelona\",\"iataCode\":\"BCN\",\"country\":{\"id\":69,\"name\":\"Spain\",\"continentId\":1,\"isoCode\":\"ESP\"}},\"destination\":{\"id\":3408,\"name\":\"John F Kennedy Intl Airport\",\"latitude\":40.63861,\"longitude\":-73.76222,\"city\":\"New York , NY\",\"iataCode\":\"JFK\",\"country\":{\"id\":229,\"name\":\"United States\",\"continentId\":3,\"isoCode\":\"USA\"}},\"carrier\":{\"id\":209,\"name\":\"American Airlines\",\"iataCode\":\"AA\"},\"date\":\"23-10-2006\",\"distance\":7000,\"duration\":\"08:15:00\",\"number\":\"AA25\"}" +
                                                   "]";

    @Override
    protected String getBasePath() {
        return "api/flights";
    }

    @Test
    void flights() {
        createGiven()

                .when()
                .get()

                .then()
                .statusCode(HttpStatus.OK.value())
                .body(equalTo(FLIGHTS_EXPECTED));
    }

    @Test
    void addFlight() {
       createGiven()
                .contentType(ContentType.JSON)
                .body(FLIGHT_TO_INSERT)
                .config(RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs("application/json", ContentType.TEXT)))

                .when()
                .post()

                .then()
                .statusCode(HttpStatus.CREATED.value());

        createGiven()

                .when()
                .get()

                .then()
                .statusCode(HttpStatus.OK.value())
                .body(equalTo(FLIGHTS_EXPECTED_AFTER_CREATION));
    }
}
