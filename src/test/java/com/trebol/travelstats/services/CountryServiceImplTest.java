package com.trebol.travelstats.services;

import com.trebol.travelstats.datatransferobjects.CountryDTO;
import com.trebol.travelstats.domainobjects.Country;
import com.trebol.travelstats.mappers.CountryMapper;
import com.trebol.travelstats.mappers.CountryMapperImpl;
import com.trebol.travelstats.repositories.CountryRepository;
import com.trebol.travelstats.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CountryMapperImpl.class)
class CountryServiceImplTest {

    private static final List<Country> COUNTRIES_FROM_DB = TestUtils.createCountryList();
    private static final List<CountryDTO> COUNTRIES_EXPECTED = TestUtils.createCountryDTOList();

    @Mock
    private CountryRepository countryRepository;

    @Autowired
    private CountryMapper countryMapper;

    private CountryService countryService;

    @BeforeEach
    void setUp() {
        countryService = new CountryServiceImpl(countryRepository, countryMapper);
    }

    @Test
    void getAllCountries() {
        // given
        when(countryRepository.findAll()).thenReturn(COUNTRIES_FROM_DB);

        // when
        final var allCountries = countryService.getAllCountries();

        // then
        assertThat(allCountries, equalTo(COUNTRIES_EXPECTED));
    }

    @Test
    void getAllCountries_WithEmptyList() {
        // given
        when(countryRepository.findAll()).thenReturn(Collections.emptyList());

        // when
        final var allCountries = countryService.getAllCountries();

        // then
        assertThat(allCountries, hasSize(0));
    }

}