package com.trebol.travelstats.datatransferobjects;

public record CountryDTO(
        Long id,
        String name,
        Integer continentId,
        String isoCode) {

}
