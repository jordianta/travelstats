package com.trebol.travelstats.controllers.rest;


import com.trebol.travelstats.datatransferobjects.PlaceDTO;
import com.trebol.travelstats.services.PlaceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @DeleteMapping("/{id}")
    public void deletePlace(@PathVariable final Long id) {
        placeService.deleteById(id);
    }

    @PostMapping
    public void addPlace(@RequestBody final PlaceDTO placeDTO) {
        placeService.add(placeDTO);
    }
}
