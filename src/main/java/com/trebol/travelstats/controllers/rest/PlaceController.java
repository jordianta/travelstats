package com.trebol.travelstats.controllers.rest;


import com.trebol.travelstats.datatransferobjects.PlaceDTO;
import com.trebol.travelstats.services.PlaceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/places")
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping
    public List<PlaceDTO> getAllPlaces() {
        return placeService.getAllPlaces();
    }
}
