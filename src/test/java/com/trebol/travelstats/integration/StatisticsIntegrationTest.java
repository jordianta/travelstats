package com.trebol.travelstats.integration;

import com.trebol.travelstats.TravelStatsApplication;
import com.trebol.travelstats.config.DataSourceConfigTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TravelStatsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:sql/clean_database.sql", "classpath:sql/insert_countries.sql", "classpath:sql/insert_airports.sql", "classpath:sql/insert_carriers.sql", "classpath:sql/insert_flights.sql"})
@ContextConfiguration(classes = DataSourceConfigTest.class)
public class StatisticsIntegrationTest {

    private static final String STATS_BY_CARRIER_EXPECTED = "[" +
            "{\"carrier\":\"American Airlines\",\"flights\":1,\"distance\":7000,\"average\":7000}," +
            "{\"carrier\":\"Qantas Airways\",\"flights\":1,\"distance\":7100,\"average\":7100}" +
            "]";

    private static final String STATS_BY_YEAR_EXPECTED = "[{\"year\":1996,\"flights\":2,\"distance\":14100}]";

    private RequestSpecification requestSpecification;

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        requestSpecification = new RequestSpecBuilder()
                .setPort(port)
                .addHeader("Content-Type", ContentType.JSON.getAcceptHeader())
                .setBasePath("api/stats")
                .build();
    }

    @Test
    public void flightsByCarrier() {
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
    public void flightsByYear() {
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
