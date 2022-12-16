package com.trebol.travelstats.datatransferobjects;

import java.sql.Time;

public record FlightDTO(
        Long id,
        AirportDTO origin,
        AirportDTO destination,
        CarrierDTO carrier,
        String date,
        Integer distance,
        Time duration,
        String number) {

}
