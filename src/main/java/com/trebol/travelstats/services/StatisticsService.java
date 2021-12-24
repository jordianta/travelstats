package com.trebol.travelstats.services;

import com.trebol.travelstats.datatransferobjects.StatsByAirportDTO;
import com.trebol.travelstats.datatransferobjects.StatsByCarrierDTO;
import com.trebol.travelstats.datatransferobjects.StatsByYearDTO;

import java.util.List;

public interface StatisticsService {

    List<StatsByCarrierDTO> getFlightsByCarrier();

    List<StatsByYearDTO> getFlightsByYear();

    List<StatsByAirportDTO> getAirports();
}