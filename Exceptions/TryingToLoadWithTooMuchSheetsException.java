package com.company.Exceptions;

public class TryingToLoadWithTooMuchSheetsException extends Exception {
    private final int maxSheets;

    public TryingToLoadWithTooMuchSheetsException(int maxSheets) {
        this.maxSheets = maxSheets;
    }

    @Override
    public String toString() {
        return "TryingToLoadWithTooMuchSheetsException{" +
                "maxSheets=" + maxSheets +
                "} " + super.toString();
    }
}
