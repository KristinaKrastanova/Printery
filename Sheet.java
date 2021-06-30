package com.company;

import com.company.Enums.PagesSize;
import com.company.Enums.SheetType;

import java.math.BigDecimal;

public class Sheet {
   private SheetType sheetType;
   private PagesSize pagesSize;
   private BigDecimal price;

    public Sheet(SheetType sheetType, PagesSize pagesSize) {
        this.sheetType = sheetType;
        this.pagesSize = pagesSize;
        this.price = this.sheetType.getPrice().add(this.pagesSize.getPrice());
    }

    public SheetType getSheetType() {
        return sheetType;
    }

    public PagesSize getPagesSize() {
        return pagesSize;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setSheetType(SheetType sheetType) {
        this.sheetType = sheetType;
        this.price = this.sheetType.getPrice().add(this.pagesSize.getPrice());
    }

    public void setPagesSize(PagesSize pagesSize) {
        this.pagesSize = pagesSize;
        this.price = this.sheetType.getPrice().add(this.pagesSize.getPrice());
    }

    @Override
    public String toString() {
        return "Sheet{" +
                "sheetType=" + sheetType +
                ", pagesSize=" + pagesSize +
                ", price=" + price +
                '}';
    }
}
