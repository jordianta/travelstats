package com.trebol.travelstats.controllers;


import com.trebol.travelstats.datatransferobjects.StatsByCarrierDTO;
import com.trebol.travelstats.datatransferobjects.StatsByYearDTO;
import com.trebol.travelstats.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("stats")
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(final StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @RequestMapping("/flights/carrier/")
    public List<StatsByCarrierDTO> getFlightsByCarrier() {
        return statisticsService.getFlightsByCarrier();
    }

    @RequestMapping("/flights/year/")
    public List<StatsByYearDTO> getFlightsByYear() {
        return statisticsService.getFlightsByYear();
    }

}
