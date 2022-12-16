package com.trebol.travelstats.datatransferobjects;

public record StatsByYearDTO(
        Integer year,
        Integer flights,
        Integer distance,
        Double time,
        Double averageTime) {

}
