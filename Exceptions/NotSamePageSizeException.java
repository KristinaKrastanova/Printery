package com.company.Exceptions;

import com.company.Enums.PagesSize;

public class NotSamePageSizeException extends Exception{
    private final PagesSize pagesSize1;
    private final PagesSize pagesSize2;

    public NotSamePageSizeException(PagesSize pagesSize1, PagesSize pagesSize2) {
        this.pagesSize1 = pagesSize1;
        this.pagesSize2 = pagesSize2;
    }

    @Override
    public String toString() {
        return "NotSamePageSize{" +
                "publication page size =" + pagesSize1 +
                ", wanted page size=" + pagesSize2 +
                "} " + super.toString();
    }
}
