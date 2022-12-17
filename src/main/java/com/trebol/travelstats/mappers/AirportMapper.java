package com.trebol.travelstats.mappers;

import com.trebol.travelstats.datatransferobjects.AirportDTO;
import com.trebol.travelstats.domainobjects.Airport;
import org.mapstruct.Mapper;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, injectionStrategy = CONSTRUCTOR, uses = CountryMapper.class)
public interface AirportMapper {

    Airport map(AirportDTO airportDTO);

    AirportDTO map(Airport airport);
}