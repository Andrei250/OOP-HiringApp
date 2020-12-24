package com.pachetepachete.Models;

public class Marketing extends Department {
    private double tax;

    public Marketing(double tax) {
        super();
        this.tax = tax;
    }

    public Marketing() {
        super();
    }

    @Override
    public double getTotalSalaryBudget() {
        double sum = 0;

        for (Employee emp : this.getEmployees()) {
            sum = sum + (1.0 * emp.getSalariu());
            if (emp.getSalariu() > 3000) {
                sum = sum - this.tax * emp.getSalariu();
            }
        }

        return sum;
    }
}
