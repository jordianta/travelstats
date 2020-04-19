package com.trebol.travelstats.controllers;

import com.trebol.travelstats.controllers.rest.PlaceController;
import com.trebol.travelstats.datatransferobjects.PlaceDTO;
import com.trebol.travelstats.services.PlaceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.trebol.travelstats.utils.TestUtils.createNYPlaceWithoutIdDTO;
import static com.trebol.travelstats.utils.TestUtils.createPlaceDTOList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PlaceControllerTest {

    private static final List<PlaceDTO> PLACES_EXPECTED = createPlaceDTOList();

    @Mock
    private PlaceService placeService;

    private PlaceController placeController;

    @Before
    public void setUp() {
        placeController = new PlaceController(placeService);
    }

    @Test
    public void getAllPlaces() {
        // given
        when(placeService.getAllPlaces()).thenReturn(PLACES_EXPECTED);

        // when then
        assertEquals(PLACES_EXPECTED, placeController.getAllPlaces());
    }

    @Test
    public void deletePlace() {
        // when
        placeController.deletePlace(1L);

        // then
        verify(placeService).deleteById(1L);
    }

    @Test
    public void addPlace() {
        // given
        final var placeDTO = createNYPlaceWithoutIdDTO();
        // when
        placeController.addPlace(placeDTO);

        // then
        verify(placeService).add(placeDTO);
    }
}