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
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:clean_database.sql", "classpath:insert_countries.sql", "classpath:insert_airports.sql"})
class AirportIntegrationTest {

    private static final String AIRPORTS_EXPECTED = "[" +
            "\"country\":{\"id\":69,\"name\":\"Spain\",\"continentId\":1,\"isoCode\":\"ESP\"}}," +
            "{\"id\":3408,\"name\":\"John F Kennedy Intl Airport\",\"latitude\":40.63861,\"longitude\":-73.76222,\"city\":\"New York , NY\",\"iataCode\":\"JFK\"," +
            "\"country\":{\"id\":229,\"name\":\"United States\",\"continentId\":3,\"isoCode\":\"USA\"}}" +
            "]";

    private RequestSpecification requestSpecification;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        requestSpecification = new RequestSpecBuilder()
                .setPort(port)
                .addHeader("Content-Type", ContentType.JSON.getAcceptHeader())
                .setBasePath("api/airports")
                .build();
    }

    @Test
    void airports() {
        given()
                .accept(ContentType.JSON)
                .spec(requestSpecification)

                .when()
                .get()

                .then()
                .statusCode(HttpStatus.OK.value())
                .body(equalTo(AIRPORTS_EXPECTED));
    }
}
