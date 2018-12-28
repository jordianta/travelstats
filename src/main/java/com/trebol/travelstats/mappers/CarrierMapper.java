package com.trebol.travelstats.mappers;

import com.trebol.travelstats.datatransferobjects.CarrierDTO;
import com.trebol.travelstats.domainobjects.Carrier;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class CarrierMapper extends ConfigurableMapper
{

    protected void configure(MapperFactory factory)
    {

        factory.classMap(Carrier.class, CarrierDTO.class)
            .constructorA("id", "name", "iataCode")
            .byDefault()
            .register();
    }
}