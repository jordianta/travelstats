package com.trebol.travelstats.services;

import com.trebol.travelstats.datatransferobjects.PlaceDTO;
import com.trebol.travelstats.domainobjects.Place;
import com.trebol.travelstats.mappers.PlaceMapper;
import com.trebol.travelstats.repositories.PlaceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.trebol.travelstats.utils.TestUtils.createPlaceDTOList;
import static com.trebol.travelstats.utils.TestUtils.createPlaceList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PlaceServiceImplTest {

    private static final List<Place> PLACES_FROM_DB = createPlaceList();
    private static final List<PlaceDTO> PLACES_EXPECTED = createPlaceDTOList();

    @Mock
    private PlaceRepository placeRepository;

    private PlaceService placeService;

    @Before
    public void setUp() {
        placeService = new PlaceServiceImpl(placeRepository, new PlaceMapper());
    }

    @Test
    public void getAllPlaces() {
        // given
        when(placeRepository.findAll()).thenReturn(PLACES_FROM_DB);

        // when
        final var allCountries = placeService.getAllPlaces();

        // then
        assertThat(allCountries, equalTo(PLACES_EXPECTED));
    }

    @Test
    public void getAllCountries_WithEmptyList() {
        // given
        when(placeRepository.findAll()).thenReturn(List.of());

        // when
        final var allCountries = placeService.getAllPlaces();

        // then
        assertThat(allCountries, hasSize(0));
    }

}