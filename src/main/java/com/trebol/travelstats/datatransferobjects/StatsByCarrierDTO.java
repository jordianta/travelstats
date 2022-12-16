package com.trebol.travelstats.datatransferobjects;

public record StatsByCarrierDTO(
        String carrier,
        Integer flights,
        Integer distance,
        Integer average,
        Double time,
        Double averageTime) {

}
