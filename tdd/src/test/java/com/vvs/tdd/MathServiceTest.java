package com.vvs.tdd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MathServiceTest {

    private MathService mathService = new DefaultMathService();

    @Test
    @DisplayName("Test add 2 positive numbers -> returns result")
    public void whenAdd2Numbers_thenReturnResult() {
        BigDecimal firstNo = BigDecimal.valueOf(2);
        BigDecimal secondNo = BigDecimal.valueOf(4);
        BigDecimal result = mathService.addNumbers(firstNo, secondNo);
        assertTrue(result.equals(BigDecimal.valueOf(6)));
    }

    @Test
    @DisplayName("Test subtract 2 positive numbers -> returns result")
    public void whenSubtract2Numbers_thenReturnResult() {
        BigDecimal firstNo = BigDecimal.valueOf(2);
        BigDecimal secondNo = BigDecimal.valueOf(4);
        BigDecimal result = mathService.subtractNumbers(firstNo, secondNo);
        assertTrue(result.equals(BigDecimal.valueOf(-2)));
    }
}
