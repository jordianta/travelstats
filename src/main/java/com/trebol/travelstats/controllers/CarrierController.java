package com.trebol.travelstats.controllers;

import com.trebol.travelstats.datatransferobjects.CarrierDTO;
import com.trebol.travelstats.services.CarrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("carriers")
public class CarrierController {

    @Autowired
    private CarrierService carrierService;

    public CarrierController(final CarrierService carrierService) {
        this.carrierService = carrierService;
    }

    @RequestMapping("")
    public List<CarrierDTO> getAllCarriers() {
        return carrierService.getAllCarriers();
    }
}