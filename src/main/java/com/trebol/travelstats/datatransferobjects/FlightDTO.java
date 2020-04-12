package com.trebol.travelstats.datatransferobjects;

import lombok.Value;

import java.sql.Time;

@Value
public class FlightDTO {

    Long id;
    AirportDTO origin;
    AirportDTO destination;
    CarrierDTO carrier;
    String date;
    Integer distance;
    Time duration;
    String number;
}
