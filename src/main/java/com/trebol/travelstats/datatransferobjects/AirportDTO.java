package com.trebol.travelstats.datatransferobjects;

import lombok.Value;

@Value
public class AirportDTO {

    Long id;
    String name;
    Float latitude;
    Float longitude;
    String city;
    String iataCode;
    CountryDTO country;
}
