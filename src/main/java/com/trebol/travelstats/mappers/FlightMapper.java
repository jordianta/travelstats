package com.trebol.travelstats.mappers;

import com.trebol.travelstats.datatransferobjects.FlightDTO;
import com.trebol.travelstats.domainobjects.Flight;
import com.trebol.travelstats.mappers.converters.DateConverter;
import com.trebol.travelstats.mappers.converters.DurationConverter;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper extends ConfigurableMapper
{

    private static final String DATE_CONVERTER_ID = "dateConverter";
    private static final String DURATION_CONVERTER_ID = "durationConverter";


    protected void configure(final MapperFactory mapperFactory)
    {

        final ConverterFactory converterFactory = mapperFactory.getConverterFactory();
        converterFactory.registerConverter(DATE_CONVERTER_ID, new DateConverter());
        converterFactory.registerConverter(DURATION_CONVERTER_ID, new DurationConverter());

        mapperFactory.classMap(Flight.class, FlightDTO.class)
            .fieldMap("date").converter(DATE_CONVERTER_ID).add()
            .fieldMap("duration").converter(DURATION_CONVERTER_ID).add()
            .constructorA("id", "origin", "destination", "carrier", "date", "distance", "duration", "number")
            .byDefault()
            .register();
    }
}