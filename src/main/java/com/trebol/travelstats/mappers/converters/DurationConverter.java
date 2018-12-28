package com.trebol.travelstats.mappers.converters;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DurationConverter extends BidirectionalConverter<Integer, String>
{

    private static final Logger LOG = LoggerFactory.getLogger(DurationConverter.class);
    private static final String DURATION_PATTERN = "HH:mm:ss";

    private final SimpleDateFormat simpleDateFormat;


    public DurationConverter()
    {
        simpleDateFormat = new SimpleDateFormat(DURATION_PATTERN);
    }


    @Override
    public String convertTo(final Integer minutes, final Type<String> type, final MappingContext mappingContext)
    {
        final Duration duration = Duration.ofMinutes(minutes);
        final Date date = new Date(duration.minusHours(1).toMillis());
        return simpleDateFormat.format(date);
    }


    @Override
    public Integer convertFrom(final String text, final Type<Integer> type, final MappingContext mappingContext)
    {
        return (int) Duration.between(LocalTime.MIN, LocalTime.parse(text)).toMinutes();
    }
}