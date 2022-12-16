package com.trebol.travelstats.controllers;

import com.trebol.travelstats.controllers.rest.CarrierController;
import com.trebol.travelstats.datatransferobjects.CarrierDTO;
import com.trebol.travelstats.services.CarrierService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.trebol.travelstats.utils.TestUtils.createCarrierDTOList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CarrierControllerTest {

    private static final List<CarrierDTO> ALL_CARRIERS = createCarrierDTOList();

    @Mock
    private CarrierService carrierService;

    private CarrierController carrierController;

    @BeforeEach
    void setUp() {
        carrierController = new CarrierController(carrierService);
    }

    @Test
    void getAllCarriers() {
        // given
        when(carrierService.getAllCarriers()).thenReturn(ALL_CARRIERS);

        // when
        final var allCarriers = carrierController.getAllCarriers();

        // then
        assertThat(allCarriers, equalTo(ALL_CARRIERS));
    }
}