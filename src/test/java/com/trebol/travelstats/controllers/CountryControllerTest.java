package com.trebol.travelstats.controllers;

import com.trebol.travelstats.datatransferobjects.CountryDTO;
import com.trebol.travelstats.services.CountryService;
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
public class CountryControllerTest {

    private static final List<CountryDTO> ALL_COUNTRIES = TestUtils.createCountryDTOList();

    @Mock
    private CountryService countryService;

    private CountryController countryController;

    @Before
    public void setUp() {
        countryController = new CountryController(countryService);
    }

    @Test
    public void getAllCountries() {
        // given
        when(countryService.getAllCountries()).thenReturn(ALL_COUNTRIES);

        // when
        final var allCountries = countryController.getAllCountries();

        // then
        assertThat(allCountries, equalTo(ALL_COUNTRIES));
    }
}