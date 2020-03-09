package com.trebol.travelstats.controllers;

import com.trebol.travelstats.datatransferobjects.CarrierDTO;
import com.trebol.travelstats.services.CarrierService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("carriers")
public class CarrierController {

    private final CarrierService carrierService;

    @RequestMapping("")
    public List<CarrierDTO> getAllCarriers() {
        return carrierService.getAllCarriers();
    }
}