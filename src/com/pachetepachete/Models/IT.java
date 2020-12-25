package com.pachetepachete.Models;

public class IT extends Department {
    private double tax;

    public IT() {
        super();
        this.tax = 0.0;
    }

    public IT(double tax) {
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

    @Override
    public String toString() {
        return "IT: " + super.toString();
    }
}
