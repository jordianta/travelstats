package com.trebol.travelstats.services;

import com.trebol.travelstats.datatransferobjects.PlaceDTO;
import com.trebol.travelstats.mappers.PlaceMapper;
import com.trebol.travelstats.repositories.PlaceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceMapper placeMapper;

    @Override
    public List<PlaceDTO> getAllPlaces() {
        return placeRepository.findAll()
                              .stream()
                              .map(placeMapper::map)
                              .toList();
    }

    @Override
    public void deleteById(final Long id) {
        placeRepository.deleteById(id);
    }

    @Override
    public void add(final PlaceDTO placeDTO) {
        placeRepository.save(placeMapper.map(placeDTO));
    }
}
