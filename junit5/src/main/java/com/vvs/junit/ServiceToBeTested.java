package com.vvs.junit;

public class ServiceToBeTested {

    public Integer addNumbers(Integer val1, Integer val2) {
        if (val1 == null || val2 == null) {
            throw new IllegalArgumentException();
        }
        return val1 + val2;
    }
}
