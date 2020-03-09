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
public class CarrierDTO {

    private Long id;
    private String name;
    private String iataCode;
}
