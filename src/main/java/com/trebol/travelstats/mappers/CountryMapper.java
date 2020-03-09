package com.trebol.travelstats.mappers;

import com.trebol.travelstats.datatransferobjects.CountryDTO;
import com.trebol.travelstats.domainobjects.Country;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper extends ConfigurableMapper {

    protected void configure(final MapperFactory factory) {

        factory.classMap(Country.class, CountryDTO.class)
               .byDefault()
               .register();
    }
}