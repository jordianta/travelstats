package com.trebol.travelstats.datatransferobjects;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class FlightDTO {

    private Long id;
    private AirportDTO origin;
    private AirportDTO destination;
    private CarrierDTO carrier;
    private String date;
    private Integer distance;
    private Time duration;
    private String number;
}
