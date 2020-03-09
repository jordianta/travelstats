package com.trebol.travelstats.services;

import com.trebol.travelstats.datatransferobjects.CountryDTO;
import com.trebol.travelstats.domainobjects.Country;
import com.trebol.travelstats.mappers.CountryMapper;
import com.trebol.travelstats.repositories.CountryRepository;
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
public class CountryServiceImplTest {

    private static final List<Country> COUNTRIES_FROM_DB = TestUtils.createCountryList();
    private static final List<CountryDTO> COUNTRIES_EXPECTED = TestUtils.createCountryDTOList();

    @Mock
    private CountryRepository countryRepository;

    private CountryService countryService;

    @Before
    public void setUp() {
        countryService = new CountryServiceImpl(countryRepository, new CountryMapper());
    }

    @Test
    public void getAllCountries() {
        // given
        when(countryRepository.findAll()).thenReturn(COUNTRIES_FROM_DB);

        // when
        final var allCountries = countryService.getAllCountries();

        // then
        assertThat(allCountries, equalTo(COUNTRIES_EXPECTED));
    }

    @Test
    public void getAllCountries_WithEmptyList() {
        // given
        when(countryRepository.findAll()).thenReturn(Collections.emptyList());

        // when
        final var allCountries = countryService.getAllCountries();

        // then
        assertThat(allCountries, hasSize(0));
    }

}