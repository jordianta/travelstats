package com.trebol.travelstats.utils;

import com.trebol.travelstats.datatransferobjects.AirportDTO;
import com.trebol.travelstats.datatransferobjects.CarrierDTO;
import com.trebol.travelstats.datatransferobjects.CountryDTO;
import com.trebol.travelstats.datatransferobjects.FlightDTO;
import com.trebol.travelstats.datatransferobjects.PlaceDTO;
import com.trebol.travelstats.domainobjects.Airport;
import com.trebol.travelstats.domainobjects.Carrier;
import com.trebol.travelstats.domainobjects.Country;
import com.trebol.travelstats.domainobjects.Flight;
import com.trebol.travelstats.domainobjects.Place;

import java.sql.Time;
import java.util.Calendar;
import java.util.List;

import static java.util.Arrays.asList;

public class TestUtils {

    public static List<Flight> createFlightList() {
        return asList(createBCNToJFKFlight(), createJFKToBCNFlight());
    }

    private static Flight createBCNToJFKFlight() {
        final var flight = new Flight();
        flight.setId(1L);
        flight.setCarrier(createAACarrier());
        flight.setOrigin(createBCNAirport());
        final var date1 = Calendar.getInstance();
        date1.clear();
        date1.set(1996, Calendar.AUGUST, 15);
        flight.setDate(date1.getTime());
        flight.setDestination(createJFKAirport());
        flight.setDistance(7000);
        flight.setDuration(Time.valueOf("08:00:00"));
        flight.setNumber("AA23");
        return flight;
    }

    private static Flight createJFKToBCNFlight() {
        final var flight = new Flight();
        flight.setId(2L);
        flight.setCarrier(createQantasCarrier());
        flight.setOrigin(createJFKAirport());
        final var date2 = Calendar.getInstance();
        date2.clear();
        date2.set(1996, Calendar.AUGUST, 23);
        flight.setDate(date2.getTime());
        flight.setDestination(createBCNAirport());
        flight.setDistance(7100);
        flight.setDuration(Time.valueOf("08:30:00"));
        flight.setNumber("QF543");
        return flight;
    }

    public static Carrier createAACarrier() {
        final var americanAirlines = new Carrier();
        americanAirlines.setId(209L);
        americanAirlines.setName("American Airlines");
        americanAirlines.setIataCode("AA");
        return americanAirlines;
    }

    public static Carrier createQantasCarrier() {
        final var qantas = new Carrier();
        qantas.setId(845L);
        qantas.setName("Qantas Airways");
        qantas.setIataCode("QF");
        return qantas;
    }

    public static List<Carrier> createCarrierList() {
        return asList(createAACarrier(), createQantasCarrier());
    }

    private static Airport createBCNAirport() {
        final var airportBCN = new Airport();
        airportBCN.setCity("Barcelona");
        airportBCN.setCountry(createSpainCountry());
        airportBCN.setIataCode("BCN");
        airportBCN.setLatitude(41.3F);
        airportBCN.setLongitude(2.083333F);
        airportBCN.setId(578L);
        airportBCN.setName("El Prat");
        return airportBCN;
    }

    private static Airport createJFKAirport() {
        final var airportJFK = new Airport();
        airportJFK.setCity("New York");
        airportJFK.setCountry(createUSACountry());
        airportJFK.setIataCode("JFK");
        airportJFK.setLatitude(40.638611F);
        airportJFK.setLongitude(-73.762222F);
        airportJFK.setId(3408L);
        airportJFK.setName("John F Kennedy Intl Airport");
        return airportJFK;
    }

    public static List<Airport> createAirportList() {
        return asList(createBCNAirport(), createJFKAirport());
    }

    private static Country createSpainCountry() {
        final var spain = new Country();
        spain.setId(69L);
        spain.setName("Spain");
        spain.setIsoCode("ESP");
        spain.setContinentId(1);
        return spain;
    }

    private static Country createUSACountry() {
        final var usa = new Country();
        usa.setId(229L);
        usa.setName("United States");
        usa.setIsoCode("USA");
        usa.setContinentId(3);
        return usa;
    }

    public static List<Country> createCountryList() {
        return asList(createSpainCountry(), createUSACountry());
    }

    public static List<FlightDTO> createFlightDTOList() {
        return asList(createBCNToJFKFlightDTO(), createJFKToBCNFlightDTO());
    }

    public static FlightDTO createBCNToJFKFlightDTO() {
        return new FlightDTO(1L, createBCNAirportDTO(), createJFKAirportDTO(), createAACarrierDTO(), "15-08-1996", 7000, Time.valueOf("08:00:00"), "AA23");
    }

    public static FlightDTO createJFKToBCNFlightDTO() {
        return new FlightDTO(2L, createJFKAirportDTO(), createBCNAirportDTO(), createQantasCarrierDTO(), "23-08-1996", 7100, Time.valueOf("08:30:00"), "QF543");
    }

    private static AirportDTO createBCNAirportDTO() {
        return new AirportDTO(578L, "El Prat", 41.3F, 2.083333F, "Barcelona", "BCN", createSpainCountryDTO());
    }

    private static AirportDTO createJFKAirportDTO() {
        return new AirportDTO(3408L, "John F Kennedy Intl Airport", 40.638611F, -73.762222F, "New York", "JFK", createUSACountryDTO());
    }

    public static List<AirportDTO> createAirportDTOList() {
        return asList(createBCNAirportDTO(), createJFKAirportDTO());
    }

    private static CountryDTO createSpainCountryDTO() {
        return new CountryDTO(69L, "Spain", 1, "ESP");
    }

    private static CountryDTO createUSACountryDTO() {
        return new CountryDTO(229L, "United States", 3, "USA");
    }

    public static List<CountryDTO> createCountryDTOList() {
        return asList(createSpainCountryDTO(), createUSACountryDTO());
    }

    private static CarrierDTO createAACarrierDTO() {
        return new CarrierDTO(209L, "American Airlines", "AA");
    }

    private static CarrierDTO createQantasCarrierDTO() {
        return new CarrierDTO(845L, "Qantas Airways", "QF");
    }

    public static List<CarrierDTO> createCarrierDTOList() {
        return asList(createAACarrierDTO(), createQantasCarrierDTO());
    }

    public static List<PlaceDTO> createPlaceDTOList() {
        return asList(createNYPlaceDTO(), createIstanbulPlaceDTO());
    }

    private static PlaceDTO createNYPlaceDTO() {
        return new PlaceDTO("New York City, NY, USA", 40.713238F, -74.00584F);
    }

    private static PlaceDTO createIstanbulPlaceDTO() {
        return new PlaceDTO("Istanbul, Turkey", 41.00986F, 28.95707F);
    }

    public static List<Place> createPlaceList() {
        return asList(createNYPlace(), createIstanbulPlace());
    }

    private static Place createNYPlace() {
        final var place = new Place();
        place.setId(1L);
        place.setName("New York City, NY, USA");
        place.setLatitude(40.713238F);
        place.setLongitude(-74.00584F);
        return place;
    }

    private static Place createIstanbulPlace() {
        final var place = new Place();
        place.setId(2L);
        place.setName("Istanbul, Turkey");
        place.setLatitude(41.00986F);
        place.setLongitude(28.95707F);
        return place;
    }
}
