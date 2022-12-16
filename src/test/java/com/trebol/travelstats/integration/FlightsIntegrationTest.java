package com.trebol.travelstats.integration;

import com.trebol.travelstats.TravelStatsApplication;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(classes = TravelStatsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:sql/clean_database.sql", "classpath:sql/insert_countries.sql", "classpath:sql/insert_airports.sql", "classpath:sql/insert_carriers.sql", "classpath:sql/insert_flights.sql"})
class FlightsIntegrationTest {

    private static final String FLIGHTS_EXPECTED = "[{\"id\":1," +
            "\"destination\":{\"id\":3408,\"name\":\"John F Kennedy Intl Airport\",\"latitude\":40.63861,\"longitude\":-73.76222,\"city\":\"New York , NY\",\"iataCode\":\"JFK\",\"country\":{\"id\":229,\"name\":\"United States\",\"continentId\":3,\"isoCode\":\"USA\"}}," +
            "\"carrier\":{\"id\":209,\"name\":\"American Airlines\",\"iataCode\":\"AA\"},\"date\":\"15-08-1996\",\"distance\":7000,\"duration\":\"08:00:00\",\"number\":\"AA23\"}," +
            "{\"id\":2,\"origin\":{\"id\":3408,\"name\":\"John F Kennedy Intl Airport\",\"latitude\":40.63861,\"longitude\":-73.76222,\"city\":\"New York , NY\",\"iataCode\":\"JFK\",\"country\":{\"id\":229,\"name\":\"United States\",\"continentId\":3,\"isoCode\":\"USA\"}}," +
            "\"destination\":{\"id\":578,\"name\":\"El Prat\",\"latitude\":41.3,\"longitude\":2.083333,\"city\":\"Barcelona\",\"iataCode\":\"BCN\",\"country\":{\"id\":69,\"name\":\"Spain\",\"continentId\":1,\"isoCode\":\"ESP\"}}," +
            "\"carrier\":{\"id\":845,\"name\":\"Qantas Airways\",\"iataCode\":\"QF\"},\"date\":\"23-08-1996\",\"distance\":7100,\"duration\":\"08:30:00\",\"number\":\"QF543\"}]";

    private static final String FLIGHT_TO_INSERT = "{\"origin\":{\"id\":578}, \"destination\":{\"id\":3408}, \"carrier\":{\"id\":209},\"date\":\"23-10-2006\",\"distance\":7000,\"duration\":\"08:00:00\",\"number\":\"AA25\"}";

    private RequestSpecification requestSpecification;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        requestSpecification = new RequestSpecBuilder()
                .setBasePath("api/flights")
                .setAccept(ContentType.JSON)
                .build();
    }

    @Test
    void flights() {
        given()
                .accept(ContentType.JSON)
                .spec(requestSpecification)

                .when()
                .get()

                .then()
                .statusCode(HttpStatus.OK.value())
                .body(equalTo(FLIGHTS_EXPECTED));
    }

    @Test
    void addFlight() {
//        given()
//                .accept(ContentType.JSON)
//                .contentType(ContentType.JSON)
//                .spec(requestSpecification)
//                .body(FLIGHT_TO_INSERT)
//                .config(RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().encodeContentTypeAs("application/json", ContentType.TEXT)))
//
//                .when()
//                .post()
//
//                .then()
//                .statusCode(HttpStatus.OK.value());
    }
}
