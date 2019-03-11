package com.trebol.travelstats.controllers;

import com.trebol.travelstats.datatransferobjects.CarrierDTO;
import com.trebol.travelstats.services.CarrierService;
import com.trebol.travelstats.services.CarrierServiceImpl;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("carriers")
public class CarrierController
{

    private static final Logger LOG = LoggerFactory.getLogger(CarrierServiceImpl.class);

    private CarrierService carrierService;


    public CarrierController(final CarrierService carrierService)
    {
        this.carrierService = carrierService;
    }


    @RequestMapping()
    public List<CarrierDTO> getAllCarriers()
    {
        return carrierService.getAllCarriers();
    }


    @PostMapping("/import")
    public void importCarriers()
    {
        LOG.info("importCarriers");
        carrierService.importCarriers();
    }
}