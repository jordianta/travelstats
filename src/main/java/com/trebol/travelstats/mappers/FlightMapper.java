package com.trebol.travelstats.mappers;

import com.trebol.travelstats.datatransferobjects.FlightDTO;
import com.trebol.travelstats.domainobjects.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, injectionStrategy = CONSTRUCTOR, uses = {AirportMapper.class, CarrierMapper.class})
public interface FlightMapper {

    @Mapping(target = "date", source = "date", dateFormat = "dd-MM-yyyy")
    Flight map(FlightDTO flightDTO);

    @Mapping(target = "date", source = "date", dateFormat = "dd-MM-yyyy")
    FlightDTO map(Flight flight);
}