package com.company.Exceptions;

public class NotLoadedWithSheetsException extends Exception{
    private final int maxSheets;

    public NotLoadedWithSheetsException(int maxSheets) {
        this.maxSheets = maxSheets;
    }

    @Override
    public String toString() {
        return "NotLoadedWithSheetsException{" +
                "maxSheets=" + maxSheets +
                "} " + super.toString();
    }
}
