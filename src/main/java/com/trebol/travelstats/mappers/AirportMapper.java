package com.trebol.travelstats.mappers;

import com.trebol.travelstats.datatransferobjects.AirportDTO;
import com.trebol.travelstats.domainobjects.Airport;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class AirportMapper extends ConfigurableMapper
{

    protected void configure(MapperFactory factory)
    {

        factory.classMap(Airport.class, AirportDTO.class)
            .constructorA("id", "name", "latitude", "longitude", "city", "iataCode", "country")
            .byDefault()
            .register();
    }
}