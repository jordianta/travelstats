package com.trebol.travelstats.services;

import com.trebol.travelstats.datatransferobjects.StatsByCarrierDTO;
import com.trebol.travelstats.datatransferobjects.StatsByYearDTO;
import com.trebol.travelstats.domainobjects.Carrier;
import com.trebol.travelstats.domainobjects.Flight;
import com.trebol.travelstats.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private FlightRepository flightRepository;


    public StatisticsServiceImpl(final FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }


    @Override
    public List<StatsByCarrierDTO> getFlightsByCarrier() {
        final List<StatsByCarrierDTO> statsByCarrierDTOList = new ArrayList<>();

        flightRepository.findAll()
                        .stream()
                        .collect(groupingBy(Flight::getCarrier))
                        .forEach((carrier, flights) -> statsByCarrierDTOList.add(createStatsByCarrierDTO(carrier, flights)));

        return statsByCarrierDTOList.stream().sorted(comparing(StatsByCarrierDTO::getCarrier)).collect(toList());
    }


    @Override
    public List<StatsByYearDTO> getFlightsByYear() {

        final List<StatsByYearDTO> statsByYearDTOList = new ArrayList<>();

        flightRepository.findAll()
                        .stream()
                        .collect(groupingBy(getYearFromFlight()))
                        .forEach((year, flights) -> statsByYearDTOList.add(createStatsByYearDTO(year, flights)));

        return statsByYearDTOList.stream().sorted(comparing(StatsByYearDTO::getYear)).collect(toList());
    }


    private Function<Flight, Integer> getYearFromFlight() {
        return flight -> {
            final Date date = flight.getDate();
            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.get(Calendar.YEAR);
        };
    }


    private StatsByCarrierDTO createStatsByCarrierDTO(final Carrier carrier, final List<Flight> flights) {
        final Integer totalDistance = getDistanceStream(flights).sum();
        final OptionalDouble average = getDistanceStream(flights).average();
        return new StatsByCarrierDTO(carrier.getName(), flights.size(), totalDistance, average.isPresent() ? (int) average.getAsDouble() : 0);
    }


    private StatsByYearDTO createStatsByYearDTO(final Integer year, final List<Flight> flights) {
        return new StatsByYearDTO(year, flights.size(), getDistanceStream(flights).sum());
    }


    private IntStream getDistanceStream(final List<Flight> flights) {
        return flights.stream().mapToInt(Flight::getDistance);
    }
}