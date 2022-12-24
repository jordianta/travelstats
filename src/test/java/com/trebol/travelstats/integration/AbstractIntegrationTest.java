package com.trebol.travelstats.integration;

import com.trebol.travelstats.TravelStatsApplication;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.given;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD;

@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = {
                "classpath:sql/clean_database.sql",
                "classpath:sql/insert_countries.sql",
                "classpath:sql/insert_airports.sql",
                "classpath:sql/insert_carriers.sql",
                "classpath:sql/insert_flights.sql",
                "classpath:sql/insert_roles.sql",
                "classpath:sql/insert_users.sql"})
@SpringBootTest(classes = TravelStatsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = AFTER_EACH_TEST_METHOD)
public abstract class AbstractIntegrationTest {


    @LocalServerPort
    protected int port;

    protected RequestSpecification requestSpecification;

    protected abstract String getBasePath();

    @BeforeEach
    void setUp() {
        requestSpecification = new RequestSpecBuilder()
                .setBasePath(getBasePath())
                .setPort(port)
                .build();
    }

    protected RequestSpecification createGiven() {
        return given()
                .accept(ContentType.JSON)
                .spec(requestSpecification)
                .auth()
                .form(
                        "test_user",
                        "test_password",
                        new FormAuthConfig("/login", "username", "password"));
    }
}
