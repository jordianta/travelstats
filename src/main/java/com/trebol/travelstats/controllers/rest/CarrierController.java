package com.trebol.travelstats.controllers.rest;

import com.trebol.travelstats.datatransferobjects.CarrierDTO;
import com.trebol.travelstats.services.CarrierService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/carriers")
public class CarrierController {

    private final CarrierService carrierService;

    @GetMapping
    public List<CarrierDTO> getAllCarriers() {
        return carrierService.getAllCarriers();
    }
}