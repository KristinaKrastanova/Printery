package com.company.Enums;

import java.math.BigDecimal;

public enum SheetType {
    NORMAL(BigDecimal.valueOf(0.50)), GLOSSY(BigDecimal.valueOf(3)), NEWSPAPER(BigDecimal.valueOf(0.25));

    private  BigDecimal price;

    SheetType(BigDecimal price) {
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
