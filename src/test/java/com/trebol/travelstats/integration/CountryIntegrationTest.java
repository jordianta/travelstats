package com.trebol.travelstats.integration;

import com.trebol.travelstats.TravelStatsApplication;
import com.trebol.travelstats.config.DataSourceConfigTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TravelStatsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:sql/clean_database.sql", "classpath:sql/insert_countries.sql"})
@ContextConfiguration(classes = DataSourceConfigTest.class)
public class CountryIntegrationTest {

    private static final String COUNTRIES_EXPECTED = "[" +
            "{\"id\":69,\"name\":\"Spain\",\"continentId\":1,\"isoCode\":\"ESP\"}," +
            "{\"id\":229,\"name\":\"United States\",\"continentId\":3,\"isoCode\":\"USA\"}" +
            "]";

    private RequestSpecification requestSpecification;

    @LocalServerPort
    private int port;

    @Before
    public void setUp() throws Exception {
        requestSpecification = new RequestSpecBuilder()
                .setPort(port)
                .addHeader("Content-Type", ContentType.JSON.getAcceptHeader())
                .build();
    }

    @Test
    public void countries() throws Exception {
        given()
                .accept(ContentType.JSON)
                .spec(requestSpecification)

                .when()
                .get("/countries")

                .then()
                .statusCode(HttpStatus.OK.value())
                .body(equalTo(COUNTRIES_EXPECTED));
    }
}
