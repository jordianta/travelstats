package com.trebol.travelstats.datatransferobjects;

import lombok.Value;

@Value
public class StatsByCarrierDTO {

    String carrier;
    Integer flights;
    Integer distance;
    Integer average;
    Double time;
    Double averageTime;
}
