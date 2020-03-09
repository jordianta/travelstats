package com.trebol.travelstats.mappers;

import com.trebol.travelstats.datatransferobjects.FlightDTO;
import com.trebol.travelstats.domainobjects.Flight;
import com.trebol.travelstats.mappers.converters.DateConverter;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper extends ConfigurableMapper {

    private static final String DATE_CONVERTER_ID = "dateConverter";

    protected void configure(final MapperFactory mapperFactory) {

        final var converterFactory = mapperFactory.getConverterFactory();
        converterFactory.registerConverter(DATE_CONVERTER_ID, new DateConverter());

        mapperFactory.classMap(Flight.class, FlightDTO.class)
                     .fieldMap("date").converter(DATE_CONVERTER_ID).add()
                     .byDefault()
                     .register();
    }
}