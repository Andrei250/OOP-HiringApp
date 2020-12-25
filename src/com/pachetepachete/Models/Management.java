package com.pachetepachete.Models;

public class Management extends Department {
    private double tax;

    public Management() {
        super();
        this.tax = 0.16;
    }

    public Management(double tax) {
        super();
        this.tax = tax;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    @Override
    public double getTotalSalaryBudget() {
        double sum = 0;

        for (Employee emp : this.getEmployees()) {
            sum = sum + (1.0 * emp.getSalariu() - this.tax * emp.getSalariu());
        }

        return sum;
    }
}
