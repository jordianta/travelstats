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
            "{\"carrier\":\"Air Asia\", \"flights\": 1, \"distance\":1318,\"average\":1318},\n" +
            "{\"carrier\":\"Air Berlin\", \"flights\": 2, \"distance\":2379,\"average\":1189},\n" +
            "{\"carrier\":\"Air China\", \"flights\": 1, \"distance\":1075,\"average\":1075},\n" +
            "{\"carrier\":\"Air France\", \"flights\": 6, \"distance\":15130,\"average\":2522},\n" +
            "{\"carrier\":\"American Airlines\", \"flights\": 4, \"distance\":19225,\"average\":4806},\n" +
            "{\"carrier\":\"ATA Airlines\", \"flights\": 1, \"distance\":527,\"average\":527},\n" +
            "{\"carrier\":\"British Airways\", \"flights\": 6, \"distance\":24245,\"average\":4041},\n" +
            "{\"carrier\":\"China Eastern Airlines\", \"flights\": 2, \"distance\":3951,\"average\":1976},\n" +
            "{\"carrier\":\"Clickair\", \"flights\": 2, \"distance\":2369,\"average\":1185},\n" +
            "{\"carrier\":\"Colgan Air\", \"flights\": 1, \"distance\":323,\"average\":323},\n" +
            "{\"carrier\":\"Commutair\", \"flights\": 1, \"distance\":129,\"average\":129},\n" +
            "{\"carrier\":\"Continental Airlines\", \"flights\": 2, \"distance\":11995,\"average\":5998},\n" +
            "{\"carrier\":\"Czech Airlines\", \"flights\": 1, \"distance\":484,\"average\":484},\n" +
            "{\"carrier\":\"Delta Airlines\", \"flights\": 4, \"distance\":20658,\"average\":516},\n" +
            "{\"carrier\":\"Easyjet\", \"flights\": 5, \"distance\":4132,\"average\":826},\n" +
            "{\"carrier\":\"Finnair\", \"flights\": 8, \"distance\":42050,\"average\":5256},\n" +
            "{\"carrier\":\"Go\", \"flights\": 2, \"distance\":2382,\"average\":1191},\n" +
            "{\"carrier\":\"Iberia\", \"flights\": 4, \"distance\":8085,\"average\":2021},\n" +
            "{\"carrier\":\"Indigo\", \"flights\": 1, \"distance\":816,\"average\":816},\n" +
            "{\"carrier\":\"Iran Air\", \"flights\": 1, \"distance\":692,\"average\":692},\n" +
            "{\"carrier\":\"Jetstar\", \"flights\": 5, \"distance\":4547,\"average\":909},\n" +
            "{\"carrier\":\"Kal Star\", \"flights\": 2, \"distance\":1204,\"average\":602},\n" +
            "{\"carrier\":\"KLM\", \"flights\": 4, \"distance\":5532,\"average\":1383},\n" +
            "{\"carrier\":\"Lion Air\", \"flights\": 2, \"distance\":870,\"average\":435},\n" +
            "{\"carrier\":\"Norwegian\", \"flights\": 2, \"distance\":2889,\"average\":1444},\n" +
            "{\"carrier\":\"Onur Air\", \"flights\": 2, \"distance\":4472,\"average\":2236},\n" +
            "{\"carrier\":\"Qantas\", \"flights\": 3, \"distance\":3435,\"average\":1145},\n" +
            "{\"carrier\":\"Qatar Airways\", \"flights\": 12, \"distance\":74750,\"average\":6229},\n" +
            "{\"carrier\":\"Ryanair\", \"flights\": 13, \"distance\":14491,\"average\":1115},\n" +
            "{\"carrier\":\"Spanair\", \"flights\": 6, \"distance\":2904,\"average\":484},\n" +
            "{\"carrier\":\"TAP Portugal\", \"flights\": 4, \"distance\":3922,\"average\":980},\n" +
            "{\"carrier\":\"Turkish Airlines\", \"flights\": 4, \"distance\":8603,\"average\":2151},\n" +
            "{\"carrier\":\"Vueling\", \"flights\": 11, \"distance\":8450,\"average\":768}\n" +
            "]";

    private static final String STATS_BY_YEAR_EXPECTED = "[\n" +
            "{\"year\": 1996, \"flights\": 2, \"distance\":482},\n" +
            "{\"year\": 2000, \"flights\": 2, \"distance\": 2382},\n" +
            "{\"year\": 2005, \"flights\": 5, \"distance\": 3542},\n" +
            "{\"year\": 2006, \"flights\": 4, \"distance\": 3936},\n" +
            "{\"year\": 2007, \"flights\": 10, \"distance\": 17066},\n" +
            "{\"year\": 2008, \"flights\": 6, \"distance\": 8541},\n" +
            "{\"year\": 2009, \"flights\": 12, \"distance\": 37458},\n" +
            "{\"year\": 2010, \"flights\": 19, \"distance\": 48803},\n" +
            "{\"year\": 2011, \"flights\": 11, \"distance\": 28603},\n" +
            "{\"year\": 2012, \"flights\": 7, \"distance\": 17275},\n" +
            "{\"year\": 2013, \"flights\": 10, \"distance\": 27363},\n" +
            "{\"year\": 2014, \"flights\": 17, \"distance\": 53898},\n" +
            "{\"year\": 2015, \"flights\": 17, \"distance\": 39546},\n" +
            "{\"year\": 2016, \"flights\": 7, \"distance\": 26841}\n" +
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
