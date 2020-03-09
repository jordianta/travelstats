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
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:sql/clean_database.sql", "classpath:sql/insert_carriers.sql"})
@ContextConfiguration(classes = DataSourceConfigTest.class)
public class CarrierIntegrationTest {

    private static final String CARRIERS_EXPECTED = "[" +
            "{\"id\":209,\"name\":\"American Airlines\",\"iataCode\":\"AA\"}," +
            "{\"id\":845,\"name\":\"Qantas Airways\",\"iataCode\":\"QF\"}" +
            "]";

    private RequestSpecification requestSpecification;

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        requestSpecification = new RequestSpecBuilder()
                .setPort(port)
                .addHeader("Content-Type", ContentType.JSON.getAcceptHeader())
                .setBasePath("api/carriers")
                .build();
    }

    @Test
    public void airports() {
        given()
                .accept(ContentType.JSON)
                .spec(requestSpecification)

                .when()
                .get()

                .then()
                .statusCode(HttpStatus.OK.value())
                .body(equalTo(CARRIERS_EXPECTED));
    }
}
