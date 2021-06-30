package com.company;

import com.company.Exceptions.NotLoadedWithSheetsException;
import com.company.Exceptions.TryingToLoadWithTooMuchSheetsException;
import com.company.Staff.OperatorPrintingMachine;

import java.util.List;

public class PrintingMachine {
    private OperatorPrintingMachine operator;
    private final int machineId;
    private static int count = 1;
    private int maxSheets;
    private boolean colorful;
    private int printingSheetsPerMin;
    private List<Sheet> currentSheets;

    public PrintingMachine(OperatorPrintingMachine operator, int maxSheets, boolean colorful, int printingSheetsPerMin, List<Sheet> currentSheets) throws NotLoadedWithSheetsException, TryingToLoadWithTooMuchSheetsException {
        this.operator = operator;
        this.machineId = PrintingMachine.count;
        PrintingMachine.count++;
        this.maxSheets = maxSheets;
        this.colorful = colorful;

        if(printingSheetsPerMin >= 1)
            this.printingSheetsPerMin = printingSheetsPerMin;
        else
            this.printingSheetsPerMin = 1;

        if(currentSheets.size() <= 0)
            throw new NotLoadedWithSheetsException(this.maxSheets);

        if(currentSheets.size() > maxSheets)
            throw new TryingToLoadWithTooMuchSheetsException(this.maxSheets);

        this.currentSheets = currentSheets;
    }

    public synchronized OperatorPrintingMachine getOperator() {
        return operator;
    }

    public synchronized int getMachineId() {
        return machineId;
    }

    public int getMaxSheets() {
        return maxSheets;
    }

    public boolean isColorful() {
        return colorful;
    }

    public int getPrintingSheetsPerMin() {
        return printingSheetsPerMin;
    }

    public List<Sheet> getCurrentSheets() {
        return currentSheets;
    }

    public void setOperator(OperatorPrintingMachine operator) {
        this.operator = operator;
    }

    public void setMaxSheets(int maxSheets) {
        if(maxSheets >= 1)
            this.maxSheets = maxSheets;
        else
            this.maxSheets = 1;
    }

    public void setColorful(boolean colorful) {
        this.colorful = colorful;
    }

    public void setPrintingSheetsPerMin(int printingSheetsPerMin) {
        if(printingSheetsPerMin >= 1)
            this.printingSheetsPerMin = printingSheetsPerMin;
        else
            this.printingSheetsPerMin = 1;
    }

    public void setCurrentSheets(List<Sheet> currentSheets) throws NotLoadedWithSheetsException, TryingToLoadWithTooMuchSheetsException {
        if(currentSheets.size() == 0)
            throw new NotLoadedWithSheetsException(this.maxSheets);

        if(currentSheets.size() > maxSheets)
            throw new TryingToLoadWithTooMuchSheetsException(this.maxSheets);

        this.currentSheets = currentSheets;
    }

    public void addToSheets(Sheet sheet) throws TryingToLoadWithTooMuchSheetsException { //notify
        if(currentSheets.size() + 1 > maxSheets)
            throw new TryingToLoadWithTooMuchSheetsException(this.maxSheets);

        this.currentSheets.add(sheet);
    }


    public synchronized boolean removeSheets(Pair<Publication, Sheet> currentPrinting) throws NotLoadedWithSheetsException, InterruptedException { //wait
        Thread.sleep((60/this.printingSheetsPerMin)*100);

        if(this.currentSheets.size() == 0)
            throw new NotLoadedWithSheetsException(this.maxSheets);

        for(Sheet currentSheet: this.currentSheets){
            if(currentSheet != null) {
                if (currentSheet.getPagesSize() == currentPrinting.getLeft().getPagesSize() &&
                        currentSheet.getSheetType() == currentPrinting.getRight().getSheetType()) {
                    this.currentSheets.remove(currentSheet);
                    return true;
                }
            }
        }
        throw new NotLoadedWithSheetsException(this.maxSheets);
    }

    @Override
    public String toString() {
        return "PrintingMachine{" +
                "operator=" + operator +
                ", machineId=" + machineId +
                ", maxSheets=" + maxSheets +
                ", colorful=" + colorful +
                ", printingSheetsPerMin=" + printingSheetsPerMin +
                ", currentSheets=" + currentSheets +
                '}';
    }
}

