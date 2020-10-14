package com.vvs.mock.testing.service;

import com.vvs.mock.testing.model.Country;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class CountryService implements CountryClient {

    private static final String ENDPOINT_URL = "https://restcountries.eu/rest/v2";

    private RestTemplate restTemplate;

    public CountryService() {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
        this.restTemplate = new RestTemplate();
    }

    @Override
    public Optional<Country> findCountryWithName(String name) {
        List<Country> countries = searchCountryByName(name);
        if (countries == null || countries.isEmpty()) {
            return Optional.empty();
        }
        Country country = countries.get(0);
        if (Objects.equals(country.getName(), "Nauru")) {
            country.setCapital(null);
        }
        if (Objects.equals(country.getName(), "South Africa")) {
            country.setCapital("Cape Town, Bloemfontein, Pretoria");
        }
        return Optional.of(country);
    }

    protected List<Country> searchCountryByName(String name) {
        try {
            return restTemplate.exchange(ENDPOINT_URL + "/name/" + name, HttpMethod.GET, null, new ParameterizedTypeReference<List<Country>>() {
            }).getBody();
        } catch (Exception ex) {
            return Collections.emptyList();
        }
    }
}
