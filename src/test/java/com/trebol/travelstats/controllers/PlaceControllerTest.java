package com.trebol.travelstats.controllers;

import com.trebol.travelstats.controllers.rest.PlaceController;
import com.trebol.travelstats.datatransferobjects.PlaceDTO;
import com.trebol.travelstats.services.PlaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.trebol.travelstats.utils.TestUtils.createNYPlaceWithoutIdDTO;
import static com.trebol.travelstats.utils.TestUtils.createPlaceDTOList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PlaceControllerTest {

    private static final List<PlaceDTO> PLACES_EXPECTED = createPlaceDTOList();

    @Mock
    private PlaceService placeService;

    private PlaceController placeController;

    @BeforeEach
    void setUp() {
        placeController = new PlaceController(placeService);
    }

    @Test
    void getAllPlaces() {
        // given
        when(placeService.getAllPlaces()).thenReturn(PLACES_EXPECTED);

        // when then
        assertEquals(PLACES_EXPECTED, placeController.getAllPlaces());
    }

    @Test
    void deletePlace() {
        // when
        placeController.deletePlace(1L);

        // then
        verify(placeService).deleteById(1L);
    }

    @Test
    void addPlace() {
        // given
        final var placeDTO = createNYPlaceWithoutIdDTO();
        // when
        placeController.addPlace(placeDTO);

        // then
        verify(placeService).add(placeDTO);
    }
}