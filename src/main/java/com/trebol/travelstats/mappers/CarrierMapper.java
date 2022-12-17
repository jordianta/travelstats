package com.trebol.travelstats.mappers;

import com.trebol.travelstats.datatransferobjects.CarrierDTO;
import com.trebol.travelstats.domainobjects.Carrier;
import org.mapstruct.Mapper;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, injectionStrategy = CONSTRUCTOR)
public interface CarrierMapper {

    Carrier map(CarrierDTO carrierDTO);

    CarrierDTO map(Carrier carrier);
}