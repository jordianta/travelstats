package com.trebol.travelstats.datatransferobjects;

import lombok.Value;

@Value
public class PlaceDTO {

    Long id;
    String name;
    Float latitude;
    Float longitude;
}
