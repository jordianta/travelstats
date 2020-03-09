package com.trebol.travelstats.controllers;

import com.trebol.travelstats.datatransferobjects.CountryDTO;
import com.trebol.travelstats.services.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("countries")
public class CountryController {

    private final CountryService countryService;

    @GetMapping
    public List<CountryDTO> getAllCountries() {
        return countryService.getAllCountries();
    }
}