package com.trebol.travelstats.services;

import com.trebol.travelstats.datatransferobjects.PlaceDTO;
import com.trebol.travelstats.domainobjects.Place;
import com.trebol.travelstats.mappers.PlaceMapper;
import com.trebol.travelstats.mappers.PlaceMapperImpl;
import com.trebol.travelstats.repositories.PlaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.trebol.travelstats.utils.TestUtils.createNYPlaceWithoutId;
import static com.trebol.travelstats.utils.TestUtils.createNYPlaceWithoutIdDTO;
import static com.trebol.travelstats.utils.TestUtils.createPlaceDTOList;
import static com.trebol.travelstats.utils.TestUtils.createPlaceList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = PlaceMapperImpl.class)
class PlaceServiceImplTest {

    private static final List<Place> PLACES_FROM_DB = createPlaceList();
    private static final List<PlaceDTO> PLACES_EXPECTED = createPlaceDTOList();

    @Mock
    private PlaceRepository placeRepository;

    @Autowired
    private PlaceMapper placeMapper;

    private PlaceService placeService;

    @BeforeEach
    void setUp() {
        placeService = new PlaceServiceImpl(placeRepository, placeMapper);
    }

    @Test
    void getAllPlaces() {
        // given
        when(placeRepository.findAll()).thenReturn(PLACES_FROM_DB);

        // when
        final var allCountries = placeService.getAllPlaces();

        // then
        assertThat(allCountries, equalTo(PLACES_EXPECTED));
    }

    @Test
    void getAllCountries_WithEmptyList() {
        // given
        when(placeRepository.findAll()).thenReturn(List.of());

        // when
        final var allCountries = placeService.getAllPlaces();

        // then
        assertThat(allCountries, hasSize(0));
    }

    @Test
    void deletePlace() {
        // when
        placeService.deleteById(1L);

        // then
        verify(placeRepository).deleteById(1L);
    }

    @Test
    void addPlace() {
        // given
        final var placeDTO = createNYPlaceWithoutIdDTO();

        // when
        placeService.add(placeDTO);

        // then
        verify(placeRepository).save(createNYPlaceWithoutId());
    }

}