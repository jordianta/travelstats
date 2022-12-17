package com.trebol.travelstats.integration;

import com.trebol.travelstats.TravelStatsApplication;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(classes = TravelStatsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:sql/clean_database.sql", "classpath:sql/insert_carriers.sql"})
class CarrierIntegrationTest {

    private static final String CARRIERS_EXPECTED = "[" +
            "{\"id\":845,\"name\":\"Qantas Airways\",\"iataCode\":\"QF\"}" +
            "]";

    private RequestSpecification requestSpecification;

    @BeforeEach
    void setUp() {
        requestSpecification = new RequestSpecBuilder()
                .setBasePath("api/carriers")
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
                .body(equalTo(CARRIERS_EXPECTED));
    }
}
