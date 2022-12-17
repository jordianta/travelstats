package com.trebol.travelstats.mappers;

import com.trebol.travelstats.datatransferobjects.CountryDTO;
import com.trebol.travelstats.domainobjects.Country;
import org.mapstruct.Mapper;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, injectionStrategy = CONSTRUCTOR)
public interface CountryMapper {

    Country map(CountryDTO countryDTO);

    CountryDTO map(Country country);
}