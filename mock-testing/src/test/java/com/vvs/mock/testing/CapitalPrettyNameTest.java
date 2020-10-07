package com.vvs.mock.testing;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CapitalPrettyNameTest {

    CapitalPrettyNameService capitalPrettyNameService = new CapitalPrettyNameService(new CountryServiceMock());

    @Test
    public void whenCountryExists_thenReturnPrettyName() {
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
        String name = capitalPrettyNameService.getCapitalPrettyName("Nauru");
        assertThat(name).isEqualTo("N/A(Nauru)");
    }

    @Test
    public void whenCountryHasMultipleCapitals_thenExpectAll() {
        String name = capitalPrettyNameService.getCapitalPrettyName("South Africa");
        assertThat(name).contains("Cape Town");
        assertThat(name).contains("Bloemfontein");
        assertThat(name).contains("Pretoria");

    }
}
