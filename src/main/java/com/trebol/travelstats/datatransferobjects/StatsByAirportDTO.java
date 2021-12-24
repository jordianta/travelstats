package com.trebol.travelstats.datatransferobjects;

import lombok.Getter;

@Getter
public class StatsByAirportDTO {

    private final String name;
    private Integer origin = 0;
    private Integer destination = 0;
    private Integer total = 0;

    public StatsByAirportDTO(final String iataCode, final String name) {
        this.name = name + " (" + iataCode + ")";
    }

    public void increaseOrigin() {
        origin++;
        increaseTotal();
    }

    public void increaseDestination() {
        destination++;
        increaseTotal();
    }

    private void increaseTotal() {
        total++;
    }
}
