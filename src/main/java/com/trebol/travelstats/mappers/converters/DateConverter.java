package com.trebol.travelstats.mappers.converters;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class DateConverter extends BidirectionalConverter<Date, String> {

    public static final String PATTERN = "dd-MM-yyyy";

    private final SimpleDateFormat simpleDateFormat;

    public DateConverter() {
        simpleDateFormat = new SimpleDateFormat(PATTERN);
    }

    @Override
    public String convertTo(final Date date, final Type<String> type, final MappingContext mappingContext) {
        return simpleDateFormat.format(date);
    }

    @Override
    public Date convertFrom(final String date, final Type<Date> type, final MappingContext mappingContext) {
        try {
            return simpleDateFormat.parse(date);
        } catch (final ParseException e) {
            log.error("Error converting date: {}", date, e);
        }
        return Calendar.getInstance().getTime();
    }
}