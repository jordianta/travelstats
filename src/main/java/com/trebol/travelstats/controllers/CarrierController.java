package com.trebol.travelstats.controllers;

import com.trebol.travelstats.datatransferobjects.CarrierDTO;
import com.trebol.travelstats.services.CarrierService;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("carriers")
public class CarrierController
{

    private CarrierService carrierService;


    public CarrierController(final CarrierService carrierService)
    {
        this.carrierService = carrierService;
    }


    @RequestMapping("")
    public List<CarrierDTO> getAllCarriers()
    {
        return carrierService.getAllCarriers();
    }


    @PostMapping("/import")
    public void importCarriers()
    {
        carrierService.importCarriers();
    }
}