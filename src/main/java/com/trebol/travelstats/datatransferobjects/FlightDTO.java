package com.trebol.travelstats.datatransferobjects;

import java.sql.Time;
import java.util.Date;

public class FlightDTO {

    private final Integer id;
    private final AirportDTO origin;
    private final AirportDTO destination;
    private final CarrierDTO carrier;
    private final String date;
    private final Integer distance;
    private final Time duration;
    private final String number;

    public FlightDTO(final Integer id, final AirportDTO origin, final AirportDTO destination, final CarrierDTO carrier, final String date, final Integer distance, final Time duration, final String number) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.carrier = carrier;
        this.date = date;
        this.distance = distance;
        this.duration = duration;
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public AirportDTO getOrigin() {
        return origin;
    }

    public AirportDTO getDestination() {
        return destination;
    }

    public CarrierDTO getCarrier() {
        return carrier;
    }

    public String getDate() {
        return date;
    }

    public Integer getDistance() {
        return distance;
    }

    public Time getDuration() {
        return duration;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "FlightDTO{" +
                "id=" + id +
                ", origin=" + origin +
                ", destination=" + destination +
                ", carrier=" + carrier +
                ", date=" + date +
                ", distance=" + distance +
                ", duration=" + duration +
                ", number='" + number + '\'' +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final FlightDTO flightDTO = (FlightDTO) o;

        if (id != null ? !id.equals(flightDTO.id) : flightDTO.id != null) return false;
        if (origin != null ? !origin.equals(flightDTO.origin) : flightDTO.origin != null) return false;
        if (destination != null ? !destination.equals(flightDTO.destination) : flightDTO.destination != null)
            return false;
        if (carrier != null ? !carrier.equals(flightDTO.carrier) : flightDTO.carrier != null) return false;
        if (date != null ? !date.equals(flightDTO.date) : flightDTO.date != null) return false;
        if (distance != null ? !distance.equals(flightDTO.distance) : flightDTO.distance != null) return false;
        if (duration != null ? !duration.equals(flightDTO.duration) : flightDTO.duration != null) return false;
        return number != null ? number.equals(flightDTO.number) : flightDTO.number == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (origin != null ? origin.hashCode() : 0);
        result = 31 * result + (destination != null ? destination.hashCode() : 0);
        result = 31 * result + (carrier != null ? carrier.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (distance != null ? distance.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }
}
