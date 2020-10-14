package com.vvs.mock.testing.service;

import com.vvs.mock.testing.model.Country;

import java.util.Optional;

public interface CountryClient {

    Optional<Country> findCountryWithName(String name);
}
