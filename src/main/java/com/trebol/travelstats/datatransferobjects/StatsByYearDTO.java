package com.trebol.travelstats.datatransferobjects;

import lombok.Value;

@Value
public class StatsByYearDTO {

    Integer year;
    Integer flights;
    Integer distance;
    Double time;
    Double averageTime;
}
