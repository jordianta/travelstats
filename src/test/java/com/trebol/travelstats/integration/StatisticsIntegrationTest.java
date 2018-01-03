package com.trebol.travelstats.integration;

import com.trebol.travelstats.TravelStatsApplication;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TravelStatsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StatisticsIntegrationTest {

    private static final String STATS_BY_CARRIER_EXPECTED = "[\n" +
            "{\"carrier\":\"Aeroflot\", \"flights\": 4, \"distance\":17670,\"average\":4418},\n" +
            "{\"carrier\":\"Air Asia\", \"flights\": 1, \"distance\":1318,\"average\":1318}\n" +
            "]";

    private static final String STATS_BY_YEAR_EXPECTED = "[\n" +
            "{\"year\": 1996, \"flights\": 2, \"distance\":482},\n" +
            "{\"year\": 2000, \"flights\": 2, \"distance\": 2382}\n" +
            "]";

    private RequestSpecification requestSpecification;

    @LocalServerPort
    private int port;

    @Before
    public void setUp() throws Exception {
        requestSpecification = new RequestSpecBuilder()
                .setPort(port)
                .addHeader("Content-Type", ContentType.JSON.getAcceptHeader())
                .setBasePath("api/stats")
                .build();
    }

    @Test
    public void flightsByCarrier() throws Exception {
        given()
                .accept(ContentType.JSON)
                .spec(requestSpecification)

                .when()
                .get("/flights/carrier/")

                .then()
                .statusCode(HttpStatus.OK.value())
                .body(equalTo(STATS_BY_CARRIER_EXPECTED));
    }

    @Test
    public void flightsByYear() throws Exception {
        given()
                .accept(ContentType.JSON)
                .spec(requestSpecification)

                .when()
                .get("/flights/year/")

                .then()
                .statusCode(HttpStatus.OK.value())
                .body(equalTo(STATS_BY_YEAR_EXPECTED));
    }
}
