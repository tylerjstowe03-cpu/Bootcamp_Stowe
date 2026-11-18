package org.example;

import java.math.BigDecimal;

public class Cash extends Asset {
    public Cash(String desciption, String dateAcquired, BigDecimal originalCose) {
        super(desciption, dateAcquired, originalCose); }

    @Override
    public BigDecimal getValue() {
        return getOriginalCose(); }
}
