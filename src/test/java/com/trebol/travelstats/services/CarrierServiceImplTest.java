package com.trebol.travelstats.services;

import com.trebol.travelstats.datatransferobjects.CarrierDTO;
import com.trebol.travelstats.domainobjects.Carrier;
import com.trebol.travelstats.mappers.CarrierMapper;
import com.trebol.travelstats.repositories.CarrierRepository;
import com.trebol.travelstats.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CarrierServiceImplTest {

    private static final List<Carrier> CARRIERS_FROM_DB = TestUtils.createCarrierList();
    private static final List<CarrierDTO> CARRIERS_EXPECTED = TestUtils.createCarrierDTOList();

    @Mock
    private CarrierRepository carrierRepository;

    private CarrierService carrierService;

    @BeforeEach
    void setUp() {
        carrierService = new CarrierServiceImpl(carrierRepository, new CarrierMapper());
    }

    @Test
    void getAllCarriers() {
        // given
        when(carrierRepository.findAll()).thenReturn(CARRIERS_FROM_DB);

        // when
        final var allCarriers = carrierService.getAllCarriers();

        // then
        assertThat(allCarriers, equalTo(CARRIERS_EXPECTED));
    }

    @Test
    void getAllCarriers_WithEmptyList() {
        // given
        when(carrierRepository.findAll()).thenReturn(Collections.emptyList());

        // when
        final var allCarriers = carrierService.getAllCarriers();

        // then
        assertThat(allCarriers, hasSize(0));
    }

}