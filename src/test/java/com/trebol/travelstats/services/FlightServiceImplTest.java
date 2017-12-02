package com.trebol.travelstats.services;

import com.trebol.travelstats.datatransferobjects.AirportDTO;
import com.trebol.travelstats.datatransferobjects.CarrierDTO;
import com.trebol.travelstats.datatransferobjects.CountryDTO;
import com.trebol.travelstats.datatransferobjects.FlightDTO;
import com.trebol.travelstats.domainobjects.Airport;
import com.trebol.travelstats.domainobjects.Carrier;
import com.trebol.travelstats.domainobjects.Country;
import com.trebol.travelstats.domainobjects.Flight;
import com.trebol.travelstats.mappers.FlightMapper;
import com.trebol.travelstats.repositories.FlightRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Time;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class FlightServiceImplTest {

    private static final List<Flight> FLIGHTS_FROM_DB = createFlightList();
    private static final List<FlightDTO> FLIGHTS_EXPECTED = createFlightDTOList();

    @Mock
    private FlightRepository flightRepository;

    private FlightService flightService;

    @Before
    public void setUp() throws Exception {
        flightService = new FlightServiceImpl(flightRepository, new FlightMapper());
    }

    @Test
    public void getAllFlights() throws Exception {
        // given
        when(flightRepository.findAll()).thenReturn(FLIGHTS_FROM_DB);

        // when
        final List<FlightDTO> allFlights = flightService.getAllFlights();

        // then
        assertThat(allFlights, is(FLIGHTS_EXPECTED));
    }

    @Test
    public void getAllFlights_WithEmptyList() throws Exception {
        // given
        when(flightRepository.findAll()).thenReturn(Collections.emptyList());

        // when
        final List<FlightDTO> allFlights = flightService.getAllFlights();

        // then
        assertThat(allFlights, hasSize(0));
    }

    private static List<Flight> createFlightList() {

        final Country spain = new Country();
        spain.setId(69);
        spain.setName("Spain");
        spain.setIsoCode("ESP");
        spain.setContinentId(1);

        final Airport airportBCN = new Airport();
        airportBCN.setCity("Barcelona");
        airportBCN.setCountry(spain);
        airportBCN.setIataCode("BCN");
        airportBCN.setLatitude(41.3F);
        airportBCN.setLongitude(2.083333F);
        airportBCN.setId(577);
        airportBCN.setName("El Prat");

        final Country usa = new Country();
        usa.setId(229);
        usa.setName("United States");
        usa.setIsoCode("USA");
        usa.setContinentId(3);

        final Airport airportJFK = new Airport();
        airportJFK.setCity("New York");
        airportJFK.setCountry(usa);
        airportJFK.setIataCode("JFK");
        airportJFK.setLatitude(40.638611F);
        airportJFK.setLongitude(-73.762222F);
        airportJFK.setId(3407);
        airportJFK.setName("John F Kennedy Intl Airport");

        final Carrier americanAirlines = new Carrier();
        americanAirlines.setId(209);
        americanAirlines.setName("American Airlines");
        americanAirlines.setIataCode("AA");

        final Carrier qantas = new Carrier();
        qantas.setId(845);
        qantas.setName("Qantas Airways");
        qantas.setIataCode("QF");

        final Flight flight1 = new Flight();
        flight1.setId(1);
        flight1.setCarrier(americanAirlines);
        flight1.setOrigin(airportBCN);
        final Calendar date1 = Calendar.getInstance();
        date1.clear();
        date1.set(1996, Calendar.AUGUST, 15);
        flight1.setDate(date1.getTime());
        flight1.setDestination(airportJFK);
        flight1.setDistance(7000);
        flight1.setDuration(Time.valueOf("08:00:00"));
        flight1.setNumber("AA23");

        final Flight flight2 = new Flight();
        flight2.setId(2);
        flight2.setCarrier(qantas);
        flight2.setOrigin(airportJFK);
        final Calendar date2 = Calendar.getInstance();
        date2.clear();
        date2.set(1996, Calendar.AUGUST, 23);
        flight2.setDate(date2.getTime());
        flight2.setDestination(airportBCN);
        flight2.setDistance(7100);
        flight2.setDuration(Time.valueOf("08:30:00"));
        flight2.setNumber("QF543");

        return asList(flight1, flight2);
    }

    private static List<FlightDTO> createFlightDTOList() {

        final CountryDTO spain = new CountryDTO(69, "Spain", 1, "ESP");
        final AirportDTO airportBCN = new AirportDTO(577, "El Prat", 41.3F, 2.083333F, "Barcelona", "BCN", spain);

        final CountryDTO usa = new CountryDTO(229, "United States", 3, "USA");
        final AirportDTO airportJFK = new AirportDTO(3407, "John F Kennedy Intl Airport", 40.638611F, -73.762222F, "New York", "JFK", usa);

        final CarrierDTO americanAirlines = new CarrierDTO(209, "American Airlines", "AA");
        final CarrierDTO qantas = new CarrierDTO(845, "Qantas Airways", "QF");

        final FlightDTO flight1 = new FlightDTO(1, airportBCN, airportJFK, americanAirlines, "15-08-1996", 7000, Time.valueOf("08:00:00"), "AA23");
        final FlightDTO flight2 = new FlightDTO(2, airportJFK, airportBCN, qantas, "23-08-1996", 7100, Time.valueOf("08:30:00"), "QF543");

        return asList(flight1, flight2);
    }

}