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
public class CountryDTO {

    private Long id;
    private String name;
    private Integer continentId;
    private String isoCode;
}
