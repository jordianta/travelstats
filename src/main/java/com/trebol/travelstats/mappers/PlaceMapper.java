package com.trebol.travelstats.mappers;

import com.trebol.travelstats.datatransferobjects.PlaceDTO;
import com.trebol.travelstats.domainobjects.Place;
import org.mapstruct.Mapper;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, injectionStrategy = CONSTRUCTOR)
public interface PlaceMapper {

    Place map(PlaceDTO placeDTO);

    PlaceDTO map(Place place);
}
