package com.trebol.travelstats.mappers;

import com.trebol.travelstats.datatransferobjects.FlightDTO;
import com.trebol.travelstats.domainobjects.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, injectionStrategy = CONSTRUCTOR, uses = {AirportMapper.class, CarrierMapper.class})
public interface FlightMapper {

    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Mapping(target = "date", source = "date", qualifiedByName = "stringToLocalDateTime")
    Flight map(FlightDTO flightDTO);

    @Mapping(target = "date", source = "date", qualifiedByName = "localDateTimeToString")
    FlightDTO map(Flight flight);

    @Named("stringToLocalDateTime")
    static LocalDate stringToLocalDateTime(final String date) {
        return date == null ? null :
                LocalDate.parse(date, DATE_TIME_FORMATTER);
    }

    @Named("localDateTimeToString")
    static String localDateTimeToString(final LocalDate dateTime) {
        return dateTime == null ? null :
                dateTime.format(DATE_TIME_FORMATTER);
    }
}