package com.trebol.travelstats.services;

import com.trebol.travelstats.datatransferobjects.CarrierDTO;
import com.trebol.travelstats.mappers.CarrierMapper;
import com.trebol.travelstats.repositories.CarrierRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CarrierServiceImpl implements CarrierService {

    private final CarrierRepository carrierRepository;
    private final CarrierMapper carrierMapper;

    @Override
    @Cacheable("carriers")
    public List<CarrierDTO> getAllCarriers() {
        return carrierRepository.findAll()
                                .stream()
                                .map(airport -> carrierMapper.map(airport, CarrierDTO.class))
                                .toList();
    }
}