package com.trebol.travelstats.controllers;

import com.trebol.travelstats.datatransferobjects.CountryDTO;
import com.trebol.travelstats.services.CountryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CountryControllerTest {

    private static final List<CountryDTO> ALL_COUNTRIES = createCountryDTOList();

    @Mock
    private CountryService countryService;

    private CountryController countryController;

    @Before
    public void setUp() throws Exception {
        countryController = new CountryController(countryService);
    }

    @Test
    public void getAllCountries() throws Exception {
        // given
        when(countryService.getAllCountries()).thenReturn(ALL_COUNTRIES);

        // when
        final List<CountryDTO> allCountries = countryController.getAllCountries();

        // then
        assertThat(allCountries, equalTo(ALL_COUNTRIES));
    }

    private static List<CountryDTO> createCountryDTOList() {
        final CountryDTO spain = new CountryDTO(69, "Spain", 1, "ESP");
        final CountryDTO usa = new CountryDTO(229, "United States", 3, "USA");

        return asList(spain, usa);
    }

}