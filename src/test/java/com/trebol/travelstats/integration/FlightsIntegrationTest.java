package com.trebol.travelstats.integration;

import com.trebol.travelstats.controllers.FlightController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FlightController.class)
public class FlightsIntegrationTest {

    private static final String ROOT_PATH = "/flights";

    private static final String FLIGHTS_EXPECTED = "[\n" +
            "    {\n" +
            "        \"distance\": 241,\n" +
            "        \"destination\": \"Menorca\",\n" +
            "        \"destinationCode\": 4487,\n" +
            "        \"destinationIata\": \"MAH\",\n" +
            "        \"origin\": \"Barcelona\",\n" +
            "        \"date\": \"15-8-1996\",\n" +
            "        \"originLng\": 2.083333,\n" +
            "        \"originCode\": 577,\n" +
            "        \"id\": 1,\n" +
            "        \"destinationLat\": 39.86667,\n" +
            "        \"carrierCode\": 566,\n" +
            "        \"carrier\": \"Iberia\",\n" +
            "        \"destinationLng\": 4.25,\n" +
            "        \"originIata\": \"BCN\",\n" +
            "        \"originLat\": 41.3\n" +
            "    },\n" +
            "    {\n" +
            "        \"distance\": 241,\n" +
            "        \"destination\": \"Barcelona\",\n" +
            "        \"destinationCode\": 577,\n" +
            "        \"destinationIata\": \"BCN\",\n" +
            "        \"origin\": \"Menorca\",\n" +
            "        \"date\": \"22-8-1996\",\n" +
            "        \"originLng\": 4.25,\n" +
            "        \"originCode\": 4487,\n" +
            "        \"id\": 2,\n" +
            "        \"destinationLat\": 41.3,\n" +
            "        \"carrierCode\": 566,\n" +
            "        \"carrier\": \"Iberia\",\n" +
            "        \"destinationLng\": 2.083333,\n" +
            "        \"originIata\": \"MAH\",\n" +
            "        \"originLat\": 39.86667\n" +
            "    }\n" +
            "]";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void flights() throws Exception {
        mockMvc.perform(get(ROOT_PATH))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(content().string(containsString(FLIGHTS_EXPECTED)));
    }
}
