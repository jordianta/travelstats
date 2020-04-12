package com.trebol.travelstats.mappers;

import com.trebol.travelstats.datatransferobjects.PlaceDTO;
import com.trebol.travelstats.domainobjects.Place;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class PlaceMapper extends ConfigurableMapper {

    protected void configure(final MapperFactory factory) {

        factory.classMap(Place.class, PlaceDTO.class)
               .byDefault()
               .register();
    }
}
