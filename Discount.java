package com.company;

import java.math.BigDecimal;

public class Discount {
    private int numberPublicationsForDiscount;
    private BigDecimal percentageDiscount;

    public Discount(int numberPublicationsForDiscount, BigDecimal percentageDiscount) {
        if(numberPublicationsForDiscount >= 0)
            this.numberPublicationsForDiscount = numberPublicationsForDiscount;
        else
            this.numberPublicationsForDiscount = 0;

        if(percentageDiscount.compareTo(BigDecimal.ZERO) >= 0)
            this.percentageDiscount = percentageDiscount;
        else
            this.percentageDiscount = BigDecimal.ZERO;
    }

    public int getNumberPublicationsForDiscount() {
        return numberPublicationsForDiscount;
    }

    public BigDecimal getPercentageDiscount() {
        return percentageDiscount;
    }

    public void setNumberPublicationsForDiscount(int numberPublicationsForDiscount) {
        if(numberPublicationsForDiscount >= 0)
            this.numberPublicationsForDiscount = numberPublicationsForDiscount;
        else
            this.numberPublicationsForDiscount = 0;
    }

    public void setPercentageDiscount(BigDecimal percentageDiscount) {
        if(percentageDiscount.compareTo(BigDecimal.ZERO) >= 0)
            this.percentageDiscount = percentageDiscount;
        else
            this.percentageDiscount = BigDecimal.ZERO;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "numberPublicationsForDiscount=" + numberPublicationsForDiscount +
                ", percentageDiscount=" + percentageDiscount +
                '}';
    }
}
