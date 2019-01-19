package com.trebol.travelstats.services;

import com.google.common.collect.ImmutableList;
import com.trebol.travelstats.datatransferobjects.CarrierDTO;
import com.trebol.travelstats.domainobjects.Carrier;
import com.trebol.travelstats.mappers.CarrierMapper;
import com.trebol.travelstats.repositories.CarrierRepository;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
public class CarrierServiceImpl implements CarrierService
{

    private static final Logger LOG = LoggerFactory.getLogger(CarrierServiceImpl.class);
    private static final String CARRIERS_CSV = "import/carriers.csv";
    private static final String CSV_FIELD_SEPARATOR = ";";

    private CarrierRepository carrierRepository;
    private CarrierMapper carrierMapper;


    public CarrierServiceImpl(final CarrierRepository carrierRepository, final CarrierMapper carrierMapper)
    {
        this.carrierRepository = carrierRepository;
        this.carrierMapper = carrierMapper;
    }


    @Override
    @Cacheable("carriers")
    public List<CarrierDTO> getAllCarriers()
    {
        return carrierRepository.findAll()
            .stream()
            .map(airport -> carrierMapper.map(airport, CarrierDTO.class))
            .collect(Collectors.toList());
    }


    @Override
    public void importCarriers()
    {
        LOG.info("Starting to import carriers");
        final List<Carrier> carrierList = readCSV();
        carrierRepository.add(carrierList);
        LOG.info("Ended import carriers");
    }


    private List<Carrier> readCSV()
    {
        final List<Carrier> content = new ArrayList<>();

        final File file;
        try
        {
            file = ResourceUtils.getFile("classpath:" + CARRIERS_CSV);
        }
        catch (FileNotFoundException e)
        {
            LOG.error("Couldn't load resource {}", CARRIERS_CSV);
            return ImmutableList.of();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = br.readLine()) != null)
            {
                content.add(createCarrier(line.split(CSV_FIELD_SEPARATOR)));
            }
        }
        catch (Exception e)
        {
            LOG.error("Error reading CSV file", e);
            return ImmutableList.of();
        }
        return content;
    }


    private Carrier createCarrier(final String[] fields)
    {
        return Carrier.newBuilder()
            .withId(Long.parseLong(fields[0]))
            .withName(fields[1])
            .withIataCode(fields[2])
            .build();
    }
}