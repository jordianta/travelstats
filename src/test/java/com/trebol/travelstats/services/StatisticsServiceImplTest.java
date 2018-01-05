package com.trebol.travelstats.services;

import com.trebol.travelstats.datatransferobjects.StatsByCarrierDTO;
import com.trebol.travelstats.datatransferobjects.StatsByYearDTO;
import com.trebol.travelstats.domainobjects.Flight;
import com.trebol.travelstats.repositories.FlightRepository;
import com.trebol.travelstats.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class StatisticsServiceImplTest {

    private StatisticsService statisticsService;

    @Mock
    private FlightRepository flightRepository;


    @Before
    public void setUp() throws Exception {
        statisticsService = new StatisticsServiceImpl(flightRepository);
    }


    @Test
    public void getFlightsByCarrier() {
        // given
        final List<Flight> flightList1 = TestUtils.createFlightList();
        flightList1.forEach(flight -> flight.setCarrier(TestUtils.createQantasCarrier()));
        flightList1.forEach(flight -> flight.setDistance(5000));
        final List<Flight> flightList2 = TestUtils.createFlightList();
        flightList2.forEach(flight -> flight.setCarrier(TestUtils.createAACarrier()));
        flightList2.forEach(flight -> flight.setDistance(6000));

        final List<Flight> flightList = new ArrayList<>(flightList1);
        flightList.addAll(flightList2);

        when(flightRepository.findAll()).thenReturn(flightList);

        // when
        final List<StatsByCarrierDTO> statsByCarrierDTOList = statisticsService.getFlightsByCarrier();

        // then
        assertEquals("American Airlines", statsByCarrierDTOList.get(0).getCarrier());
        assertEquals(Integer.valueOf(12000), statsByCarrierDTOList.get(0).getDistance());
        assertEquals(Integer.valueOf(2), statsByCarrierDTOList.get(0).getFlights());
        assertEquals(Integer.valueOf(6000), statsByCarrierDTOList.get(0).getAverage());

        assertEquals("Qantas Airways", statsByCarrierDTOList.get(1).getCarrier());
        assertEquals(Integer.valueOf(10000), statsByCarrierDTOList.get(1).getDistance());
        assertEquals(Integer.valueOf(2), statsByCarrierDTOList.get(1).getFlights());
        assertEquals(Integer.valueOf(5000), statsByCarrierDTOList.get(1).getAverage());

    }


    @Test
    public void getFlightsByYear() {
        // given
        final List<Flight> flightList1 = TestUtils.createFlightList();
        final List<Flight> flightList2 = TestUtils.createFlightList();
        flightList2.forEach(flight -> flight.getDate().setYear(97));

        final List<Flight> flightList = new ArrayList<>(flightList1);
        flightList.addAll(flightList2);

        when(flightRepository.findAll()).thenReturn(flightList);

        // when
        final List<StatsByYearDTO> statsByYearDTOList = statisticsService.getFlightsByYear();

        // then
        assertEquals(Integer.valueOf(1996), statsByYearDTOList.get(0).getYear());
        assertEquals(Integer.valueOf(14100), statsByYearDTOList.get(0).getDistance());
        assertEquals(Integer.valueOf(2), statsByYearDTOList.get(0).getFlights());

        assertEquals(Integer.valueOf(1997), statsByYearDTOList.get(1).getYear());
        assertEquals(Integer.valueOf(14100), statsByYearDTOList.get(1).getDistance());
        assertEquals(Integer.valueOf(2), statsByYearDTOList.get(1).getFlights());
    }

}