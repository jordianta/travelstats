package com.trebol.travelstats.controllers;


import com.trebol.travelstats.datatransferobjects.StatsByCarrierDTO;
import com.trebol.travelstats.datatransferobjects.StatsByYearDTO;
import com.trebol.travelstats.services.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("stats")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/flights/carrier/")
    public List<StatsByCarrierDTO> getFlightsByCarrier() {
        return statisticsService.getFlightsByCarrier();
    }

    @GetMapping("/flights/year/")
    public List<StatsByYearDTO> getFlightsByYear() {
        return statisticsService.getFlightsByYear();
    }

}
