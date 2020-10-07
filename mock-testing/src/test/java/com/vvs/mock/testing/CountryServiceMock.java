package com.vvs.mock.testing;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vvs.mock.testing.model.Country;
import com.vvs.mock.testing.service.CountryService;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CountryServiceMock extends CountryService {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected List<Country> searchCountryByName(String name) {
        try {
            List<Country> countries = objectMapper.readValue(this.getClass().getClassLoader().getResourceAsStream("countries.json"), new TypeReference<List<Country>>() {
            });
            return countries.stream()
                    .filter(country -> country.getName().startsWith(name))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            return Collections.emptyList();
        }
    }
}
