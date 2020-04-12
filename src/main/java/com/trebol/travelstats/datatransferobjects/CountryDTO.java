package com.trebol.travelstats.datatransferobjects;

import lombok.Value;

@Value
public class CountryDTO {

    Long id;
    String name;
    Integer continentId;
    String isoCode;
}
