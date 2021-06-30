package com.company.Enums;

import java.math.BigDecimal;

public enum PagesSize {
    A1(BigDecimal.valueOf(0.50)), A2(BigDecimal.valueOf(1)), A3(BigDecimal.valueOf(1.50)), A4(BigDecimal.valueOf(2)), A5(BigDecimal.valueOf(2.5));

    private BigDecimal price;

    PagesSize(BigDecimal price) {
        if(price.compareTo(BigDecimal.ZERO) == 1)
            this.price = price;
        else
            this.price = BigDecimal.ZERO;
    }

    public BigDecimal getPrice(){
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        if(price.compareTo(BigDecimal.ZERO) == 1)
            this.price = price;
        else
            this.price = BigDecimal.ZERO;
    }
}
