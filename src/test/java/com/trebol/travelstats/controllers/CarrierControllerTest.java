package com.trebol.travelstats.controllers;

import com.trebol.travelstats.datatransferobjects.CarrierDTO;
import com.trebol.travelstats.services.CarrierService;
import com.trebol.travelstats.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CarrierControllerTest {

    private static final List<CarrierDTO> ALL_CARRIERS = TestUtils.createCarrierDTOList();

    @Mock
    private CarrierService carrierService;

    private CarrierController carrierController;

    @Before
    public void setUp() {
        carrierController = new CarrierController(carrierService);
    }

    @Test
    public void getAllCarriers() {
        // given
        when(carrierService.getAllCarriers()).thenReturn(ALL_CARRIERS);

        // when
        final List<CarrierDTO> allCarriers = carrierController.getAllCarriers();

        // then
        assertThat(allCarriers, equalTo(ALL_CARRIERS));
    }
}