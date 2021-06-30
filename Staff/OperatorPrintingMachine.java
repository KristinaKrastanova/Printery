package com.company.Staff;

import java.math.BigDecimal;

public class OperatorPrintingMachine extends Employee {
    private boolean busy;

    public OperatorPrintingMachine(String name, String id) {
        super(name, id);
        this.busy = false;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    @Override
    public BigDecimal getTotalSalary() {
        return getMainSalary();
    }

    @Override
    public String toString() {
        return "OperatorPrintingMachine{" +
                "busy=" + busy +
                "} " + super.toString();
    }
}
