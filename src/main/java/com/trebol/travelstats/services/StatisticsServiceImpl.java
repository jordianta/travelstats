package com.trebol.travelstats.services;

import com.trebol.travelstats.datatransferobjects.StatsByAirportDTO;
import com.trebol.travelstats.datatransferobjects.StatsByCarrierDTO;
import com.trebol.travelstats.datatransferobjects.StatsByYearDTO;
import com.trebol.travelstats.domainobjects.Carrier;
import com.trebol.travelstats.domainobjects.Flight;
import com.trebol.travelstats.repositories.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;

@AllArgsConstructor
@Service
public class StatisticsServiceImpl implements StatisticsService {

    private final FlightRepository flightRepository;

    @Override
    public List<StatsByCarrierDTO> getFlightsByCarrier() {
        final List<StatsByCarrierDTO> statsByCarrierDTOList = new ArrayList<>();

        flightRepository.findAll()
                        .stream()
                        .collect(groupingBy(Flight::getCarrier))
                        .forEach((carrier, flights) -> statsByCarrierDTOList.add(createStatsByCarrierDTO(carrier, flights)));

        return statsByCarrierDTOList.stream()
                                    .sorted(comparing(StatsByCarrierDTO::carrier))
                                    .toList();
    }


    @Override
    public List<StatsByYearDTO> getFlightsByYear() {

        final List<StatsByYearDTO> statsByYearDTOList = new ArrayList<>();

        flightRepository.findAll()
                        .stream()
                        .collect(groupingBy(getYearFromFlight()))
                        .forEach((year, flights) -> statsByYearDTOList.add(createStatsByYearDTO(year, flights)));

        return statsByYearDTOList.stream()
                                 .sorted(comparing(StatsByYearDTO::year))
                                 .toList();
    }

    @Override
    public List<StatsByAirportDTO> getAirports() {
        final var airports = new HashMap<String, StatsByAirportDTO>();

        flightRepository.findAll()
                        .forEach(flight -> {
                            airports.computeIfAbsent(flight.getOrigin().getIataCode(), iataCode -> new StatsByAirportDTO(iataCode, flight.getOrigin().getName())).increaseOrigin();
                            airports.computeIfAbsent(flight.getDestination().getIataCode(), iataCode -> new StatsByAirportDTO(iataCode, flight.getDestination().getName())).increaseDestination();
                        });

        return airports.values().stream()
                       .sorted(comparing(StatsByAirportDTO::getName))
                       .toList();
    }


    private Function<Flight, Integer> getYearFromFlight() {
        return flight -> {
            final var date = flight.getDate();
            final var calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.get(Calendar.YEAR);
        };
    }


    private StatsByCarrierDTO createStatsByCarrierDTO(final Carrier carrier, final List<Flight> flights) {
        final var totalDistance = calculateTotalDistance(flights);
        final var averageDistance = calculateAverageDistance(flights);
        final var totalTime = calculateTotalTime(flights);
        final var averageTime = calculateAverageTime(flights);
        return new StatsByCarrierDTO(carrier.getName(), flights.size(), totalDistance, averageDistance, totalTime, averageTime);
    }


    private StatsByYearDTO createStatsByYearDTO(final Integer year, final List<Flight> flights) {
        final var totalDistance = calculateTotalDistance(flights);
        final var totalTime = calculateTotalTime(flights);
        final var averageTime = calculateAverageTime(flights);
        return new StatsByYearDTO(year, flights.size(), totalDistance, totalTime, averageTime);
    }

    private int calculateTotalDistance(final List<Flight> flights) {
        return flights.stream()
                      .mapToInt(Flight::getDistance)
                      .sum();
    }

    private int calculateAverageDistance(final List<Flight> flights) {
        return (int) flights.stream()
                            .mapToInt(Flight::getDistance)
                            .average()
                            .orElse(0);
    }

    private double calculateTotalTime(final List<Flight> flights) {
        return flights.stream()
                      .mapToDouble(StatisticsServiceImpl::getFlightDuration)
                      .sum();
    }

    private double calculateAverageTime(final List<Flight> flights) {
        return flights.stream()
                      .mapToDouble(StatisticsServiceImpl::getFlightDuration)
                      .average()
                      .orElse(0);
    }

    private static double getFlightDuration(final Flight flight) {
        return (flight.getDuration().getMinutes() + flight.getDuration().getHours() * 60) / 60;
    }


}