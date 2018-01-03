package com.trebol.travelstats.controllers;

import com.trebol.travelstats.datatransferobjects.CountryDTO;
import com.trebol.travelstats.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    public CountryController(final CountryService countryService) {
        this.countryService = countryService;
    }

    @RequestMapping("")
    public List<CountryDTO> getAllCountries() {
        return countryService.getAllCountries();
    }
}