package com.trebol.travelstats.integration;

import com.trebol.travelstats.controllers.StatisticsController;
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
@WebMvcTest(StatisticsController.class)
public class StatisticsIntegrationTest {

    private static final String ROOT_PATH = "/stats";

    private static final String STATS_BY_CARRIER_EXPECTED = "[\n" +
            "{\"carrier\":\"Aeroflot\", \"flights\": 4, \"distance\":17670,\"average\":4418},\n" +
            "{\"carrier\":\"Air Asia\", \"flights\": 1, \"distance\":1318,\"average\":1318}\n" +
            "]";

    private static final String STATS_BY_YEAR_EXPECTED = "[\n" +
            "{\"year\": 1996, \"flights\": 2, \"distance\":482},\n" +
            "{\"year\": 2000, \"flights\": 2, \"distance\": 2382}\n" +
            "]";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void flightsByCarrier() throws Exception {
        mockMvc.perform(get(ROOT_PATH + "/flights/carrier/"))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(content().string(containsString(STATS_BY_CARRIER_EXPECTED)));
    }

    @Test
    public void flightsByYear() throws Exception {
        mockMvc.perform(get(ROOT_PATH + "/flights/year/"))
               .andDo(print())
               .andExpect(status().isOk())
               .andExpect(content().string(containsString(STATS_BY_YEAR_EXPECTED)));
    }
}
