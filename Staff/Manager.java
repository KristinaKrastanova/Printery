package com.company.Staff;

import java.math.BigDecimal;

public class Manager extends Employee {
    private BigDecimal additionalPercentage;
    private static boolean deservesBonus;

    public Manager(String name, String id, BigDecimal additionalPercentage) {
        super(name, id);
        if(additionalPercentage.compareTo(BigDecimal.ZERO) >= 0)
            this.additionalPercentage = additionalPercentage;
        else
            this.additionalPercentage = BigDecimal.ZERO;

        deservesBonus = false;
    }

    public BigDecimal getAdditionalPercentage() {
        return additionalPercentage;
    }

    public void setAdditionalPercentage(BigDecimal additionalPercentage) {
        if(additionalPercentage.compareTo(BigDecimal.ZERO) >= 0)
            this.additionalPercentage = additionalPercentage;
        else
            this.additionalPercentage = BigDecimal.ZERO;
    }

    public static boolean deservesBonus() {
        return deservesBonus;
    }

    public static void setDeservesBonus(boolean deservesBonus) {
        Manager.deservesBonus = deservesBonus;
    }

    @Override
    public BigDecimal getTotalSalary() {
        if(Manager.deservesBonus) {
            BigDecimal increment = additionalPercentage.divide(BigDecimal.valueOf(100)).multiply(getMainSalary());
            return mainSalary.add(increment);
        }else{
            return mainSalary;
        }
    }

    @Override
    public String toString() {
        return "Manager{" +
                "additionalPercentage=" + additionalPercentage +
                "} " + super.toString();
    }
}
