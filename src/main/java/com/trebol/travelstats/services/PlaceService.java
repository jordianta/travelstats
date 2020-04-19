package com.trebol.travelstats.services;

import com.trebol.travelstats.datatransferobjects.PlaceDTO;

import java.util.List;

public interface PlaceService {

    List<PlaceDTO> getAllPlaces();

    void deleteById(Long id);
}
