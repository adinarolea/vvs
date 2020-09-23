package com.vvs.junit;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServiceToBeTestedTest {

    ServiceToBeTested serviceToBeTested = new ServiceToBeTested();

    @BeforeAll
    public static void init() {
        System.out.println("BeforeAll call");
    }

    @BeforeEach
    public void beforeMethod() {
        System.out.println("BeforeEach call");
    }

    @AfterAll
    public static void cleanup() {
        System.out.println("AfterAll call");
    }

    @AfterEach
    public void afterMethod() {
        System.out.println("AfterEach call");
    }

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
        assertThrows(IllegalArgumentException.class, () -> serviceToBeTested.addNumbers(firstNo, secondNo));
    }

    @Test
    @DisplayName("Test second no null -> throw exception")
    public void whenAddSecondNull_thenThrowException() {
        Integer firstNo = 22;
        Integer secondNo = null;
        assertThrows(IllegalArgumentException.class, () -> serviceToBeTested.addNumbers(firstNo, secondNo));
    }
}
