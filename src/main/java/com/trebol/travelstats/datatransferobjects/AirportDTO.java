package com.trebol.travelstats.datatransferobjects;

public record AirportDTO(
        Long id,
        String name,
        Float latitude,
        Float longitude,
        String city,
        String iataCode,
        CountryDTO country) {

}
