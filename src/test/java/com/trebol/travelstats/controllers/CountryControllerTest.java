package com.trebol.travelstats.controllers;

import com.trebol.travelstats.controllers.rest.CountryController;
import com.trebol.travelstats.datatransferobjects.CountryDTO;
import com.trebol.travelstats.services.CountryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.trebol.travelstats.utils.TestUtils.createCountryDTOList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CountryControllerTest {

    private static final List<CountryDTO> ALL_COUNTRIES = createCountryDTOList();

    @Mock
    private CountryService countryService;

    private CountryController countryController;

    @BeforeEach
    void setUp() {
        countryController = new CountryController(countryService);
    }

    @Test
    void getAllCountries() {
        // given
        when(countryService.getAllCountries()).thenReturn(ALL_COUNTRIES);

        // when
        final var allCountries = countryController.getAllCountries();

        // then
        assertThat(allCountries, equalTo(ALL_COUNTRIES));
    }
}