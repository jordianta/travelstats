package com.trebol.travelstats.services;

import com.trebol.travelstats.datatransferobjects.CarrierDTO;
import com.trebol.travelstats.mappers.CarrierMapper;
import com.trebol.travelstats.repositories.CarrierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarrierServiceImpl implements CarrierService {

    private CarrierRepository carrierRepository;
    private CarrierMapper carrierMapper;

    public CarrierServiceImpl(final CarrierRepository carrierRepository, final CarrierMapper carrierMapper) {
        this.carrierRepository = carrierRepository;
        this.carrierMapper = carrierMapper;
    }

    @Override
    @Cacheable("carriers")
    public List<CarrierDTO> getAllCarriers() {
        return carrierRepository.findAll()
                                .stream()
                                .map(airport -> carrierMapper.map(airport, CarrierDTO.class))
                                .collect(Collectors.toList());
    }
}