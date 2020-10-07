package com.vvs.mock.testing;

import com.vvs.mock.testing.model.Country;
import com.vvs.mock.testing.service.CountryClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CapitalPrettyNameTest {

    @Mock
    CountryClient countryClient;

    private CapitalPrettyNameService capitalPrettyNameService;

    @BeforeEach
    public void init(){
        this.capitalPrettyNameService = new CapitalPrettyNameService(countryClient);
    }

    private void mockCapital(String country, String capital) {
        when(countryClient.findCountryWithName(country))
                .thenReturn(Optional.of(Country.builder()
                        .capital(capital)
                        .name(country)
                        .build()));
    }

    @Test
    public void whenCountryExists_thenReturnPrettyName() {
        mockCapital("Romania", "Bucharest");
        String name = capitalPrettyNameService.getCapitalPrettyName("Romania");
        assertThat(name).isEqualTo("Bucharest(Romania)");
    }

    @Test
    public void whenCountryDoesNotExist_thenExpectException() {
        assertThatThrownBy(() -> capitalPrettyNameService.getCapitalPrettyName("Invalid"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenCountryDoesNotHaveCapital_thenExpectNA() {
        mockCapital("Nauru", "N/A");
        String name = capitalPrettyNameService.getCapitalPrettyName("Nauru");
        assertThat(name).isEqualTo("N/A(Nauru)");
    }

    @Test
    public void whenCountryHasMultipleCapitals_thenExpectAll() {
        mockCapital("South Africa", "Cape Town, Bloemfontein, Pretoria");
        String name = capitalPrettyNameService.getCapitalPrettyName("South Africa");
        assertThat(name).contains("Cape Town");
        assertThat(name).contains("Bloemfontein");
        assertThat(name).contains("Pretoria");
    }
}
