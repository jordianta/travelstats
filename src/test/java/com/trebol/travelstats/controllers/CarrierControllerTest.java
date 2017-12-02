package com.trebol.travelstats.controllers;

import com.trebol.travelstats.datatransferobjects.CarrierDTO;
import com.trebol.travelstats.services.CarrierService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CarrierControllerTest {
    private static final List<CarrierDTO> ALL_CARRIERS = createCarrierDTOList();

    @Mock
    private CarrierService carrierService;

    private CarrierController carrierController;

    @Before
    public void setUp() throws Exception {
        carrierController = new CarrierController(carrierService);
    }

    @Test
    public void getAllCarriers() throws Exception {
        // given
        when(carrierService.getAllCarriers()).thenReturn(ALL_CARRIERS);

        // when
        final List<CarrierDTO> allCarriers = carrierController.getAllCarriers();

        // then
        assertThat(allCarriers, equalTo(ALL_CARRIERS));
    }

    private static List<CarrierDTO> createCarrierDTOList() {
        final CarrierDTO americanAirlines = new CarrierDTO(209, "American Airlines", "AA");
        final CarrierDTO qantas = new CarrierDTO(845, "Qantas Airways", "QF\"");

        return Arrays.asList(americanAirlines, qantas);
    }

}