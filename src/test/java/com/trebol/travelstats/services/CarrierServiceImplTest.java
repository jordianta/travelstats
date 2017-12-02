package com.trebol.travelstats.services;

import com.trebol.travelstats.datatransferobjects.CarrierDTO;
import com.trebol.travelstats.domainobjects.Carrier;
import com.trebol.travelstats.mappers.CarrierMapper;
import com.trebol.travelstats.repositories.CarrierRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CarrierServiceImplTest {

    private static final List<Carrier> CARRIERS_FROM_DB = createCarrierList();
    private static final List<CarrierDTO> CARRIERS_EXPECTED = createCarrierDTOList();

    @Mock
    private CarrierRepository carrierRepository;

    private CarrierService carrierService;

    @Before
    public void setUp() throws Exception {
        carrierService = new CarrierServiceImpl(carrierRepository, new CarrierMapper());
    }

    @Test
    public void getAllCarriers() throws Exception {
        // given
        when(carrierRepository.findAll()).thenReturn(CARRIERS_FROM_DB);

        // when
        final List<CarrierDTO> allCarriers = carrierService.getAllCarriers();

        // then
        assertThat(allCarriers, equalTo(CARRIERS_EXPECTED));
    }

    @Test
    public void getAllCarriers_WithEmptyList() throws Exception {
        // given
        when(carrierRepository.findAll()).thenReturn(Collections.emptyList());

        // when
        final List<CarrierDTO> allCarriers = carrierService.getAllCarriers();

        // then
        assertThat(allCarriers, hasSize(0));
    }

    private static List<Carrier> createCarrierList() {
        final Carrier americanAirlines = new Carrier();
        americanAirlines.setId(209);
        americanAirlines.setName("American Airlines");
        americanAirlines.setIataCode("AA");

        final Carrier qantas = new Carrier();
        qantas.setId(845);
        qantas.setName("Qantas Airways");
        qantas.setIataCode("QF");

        return asList(americanAirlines, qantas);
    }

    private static List<CarrierDTO> createCarrierDTOList() {
        final CarrierDTO americanAirlines = new CarrierDTO(209, "American Airlines", "AA");
        final CarrierDTO qantas = new CarrierDTO(845, "Qantas Airways", "QF");

        return asList(americanAirlines, qantas);
    }

}