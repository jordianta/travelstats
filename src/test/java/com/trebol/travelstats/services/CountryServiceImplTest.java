package com.trebol.travelstats.services;

import com.trebol.travelstats.datatransferobjects.CountryDTO;
import com.trebol.travelstats.domainobjects.Country;
import com.trebol.travelstats.mappers.CountryMapper;
import com.trebol.travelstats.repositories.CountryRepository;
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
public class CountryServiceImplTest {

    private static final List<Country> COUNTRIES_FROM_DB = createCountryList();
    private static final List<CountryDTO> COUNTRIES_EXPECTED = createCountryDTOList();

    @Mock
    private CountryRepository countryRepository;

    private CountryService countryService;

    @Before
    public void setUp() throws Exception {
        countryService = new CountryServiceImpl(countryRepository, new CountryMapper());
    }

    @Test
    public void getAllCountries() throws Exception {
        // given
        when(countryRepository.findAll()).thenReturn(COUNTRIES_FROM_DB);

        // when
        final List<CountryDTO> allCountries = countryService.getAllCountries();

        // then
        assertThat(allCountries, equalTo(COUNTRIES_EXPECTED));
    }

    @Test
    public void getAllCountries_WithEmptyList() throws Exception {
        // given
        when(countryRepository.findAll()).thenReturn(Collections.emptyList());

        // when
        final List<CountryDTO> allCountries = countryService.getAllCountries();

        // then
        assertThat(allCountries, hasSize(0));
    }

    private static List<Country> createCountryList() {
        final Country spain = new Country();
        spain.setId(69);
        spain.setName("Spain");
        spain.setIsoCode("ESP");
        spain.setContinentId(1);

        final Country usa = new Country();
        usa.setId(229);
        usa.setName("United States");
        usa.setIsoCode("USA");
        usa.setContinentId(3);

        return asList(spain, usa);
    }

    private static List<CountryDTO> createCountryDTOList() {
        final CountryDTO spain = new CountryDTO(69, "Spain", 1, "ESP");
        final CountryDTO usa = new CountryDTO(229, "United States", 3, "USA");

        return asList(spain, usa);
    }

}