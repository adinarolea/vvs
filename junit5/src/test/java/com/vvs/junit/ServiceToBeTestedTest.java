package com.vvs.junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ServiceToBeTestedTest {

    ServiceToBeTested serviceToBeTested = new ServiceToBeTested();

    @Test
    @DisplayName("Test add 2 positive numbers -> returns result")
    public void whenAdd2Numbers_thenReturnResult() {
        Integer firstNo = 2;
        Integer secondNo = 4;
        Integer result = serviceToBeTested.addNumbers(firstNo, secondNo);
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("Test first no null -> throw exception")
    public void whenAddFirstNull_thenThrowException() {
        Integer firstNo = null;
        Integer secondNo = 4;
        assertThatThrownBy(() -> serviceToBeTested.addNumbers(firstNo, secondNo))
                .isExactlyInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Test second no null -> throw exception")
    public void whenAddSecondNull_thenThrowException() {
        Integer firstNo = 22;
        Integer secondNo = null;
        assertThatThrownBy(() -> serviceToBeTested.addNumbers(firstNo, secondNo))
                .isExactlyInstanceOf(NullPointerException.class);
    }
}
