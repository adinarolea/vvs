package com.vvs.mock.testing.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Country {

    private String name;
    private String capital;
    private String region;
    private String subregion;

}