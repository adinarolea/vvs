package com.vvs.tdd;

import java.math.BigDecimal;

public class DefaultMathService implements MathService{
    public BigDecimal addNumbers(BigDecimal first, BigDecimal second) {
        return first.add(second);
    }

    public BigDecimal subtractNumbers(BigDecimal first, BigDecimal second) {
        return first.subtract(second);
    }
}
