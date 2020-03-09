package com.trebol.travelstats.datatransferobjects;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class AirportDTO {

    private Long id;
    private String name;
    private Float latitude;
    private Float longitude;
    private String city;
    private String iataCode;
    private CountryDTO country;
}
