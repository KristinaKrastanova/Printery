package com.company.Staff;

import java.math.BigDecimal;

public abstract class Employee {
    protected String name;
    protected String id;
    protected static BigDecimal mainSalary;

    public Employee(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getMainSalary() {
        return mainSalary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static void setMainSalary(BigDecimal mainSalary) {
        if(mainSalary.compareTo(BigDecimal.ZERO) >= 0)
            Employee.mainSalary = mainSalary;
        else
            Employee.mainSalary = BigDecimal.ZERO;
    }


    public abstract BigDecimal getTotalSalary();

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
