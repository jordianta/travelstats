package com.trebol.travelstats.controllers;


import com.trebol.travelstats.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stats")
public class StatisticsController {

    @Autowired
    private final StatisticsService statisticsService;

    public StatisticsController(final StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @RequestMapping("/flights/carrier/")
    public String getFlightsByCarrier() {
        return statisticsService.getFlightsByCarrier();
    }

    @RequestMapping("/flights/year/")
    public String getFlightsByYear() {
        return statisticsService.getFlightsByYear();
    }

}
