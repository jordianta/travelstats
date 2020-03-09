package com.trebol.travelstats.services;

import com.trebol.travelstats.datatransferobjects.CarrierDTO;
import com.trebol.travelstats.domainobjects.Carrier;
import com.trebol.travelstats.mappers.CarrierMapper;
import com.trebol.travelstats.repositories.CarrierRepository;
import com.trebol.travelstats.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CarrierServiceImplTest {

    private static final List<Carrier> CARRIERS_FROM_DB = TestUtils.createCarrierList();
    private static final List<CarrierDTO> CARRIERS_EXPECTED = TestUtils.createCarrierDTOList();

    @Mock
    private CarrierRepository carrierRepository;

    private CarrierService carrierService;

    @Before
    public void setUp() {
        carrierService = new CarrierServiceImpl(carrierRepository, new CarrierMapper());
    }

    @Test
    public void getAllCarriers() {
        // given
        when(carrierRepository.findAll()).thenReturn(CARRIERS_FROM_DB);

        // when
        final List<CarrierDTO> allCarriers = carrierService.getAllCarriers();

        // then
        assertThat(allCarriers, equalTo(CARRIERS_EXPECTED));
    }

    @Test
    public void getAllCarriers_WithEmptyList() {
        // given
        when(carrierRepository.findAll()).thenReturn(Collections.emptyList());

        // when
        final List<CarrierDTO> allCarriers = carrierService.getAllCarriers();

        // then
        assertThat(allCarriers, hasSize(0));
    }

}