package com.vvs.mock.testing;

import com.vvs.mock.testing.model.Country;
import com.vvs.mock.testing.service.CountryClient;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CapitalPrettyNameService {

    CountryClient countryClient;

    public String getCapitalPrettyName(String countryName) {
        Country country = countryClient.findCountryWithName(countryName)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Could not find country with name %s ", countryName)));
        String capitalName = country.getCapital() != null ? country.getCapital() : "N/A";
        return capitalName + "(" + country.getName() + ")";
    }
}
