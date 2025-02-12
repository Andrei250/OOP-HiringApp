package com.pachetepachete.Models;

/*
    Departamentul Marketing.
 */
public class Marketing extends Department {
    private double tax;

    public Marketing(double tax) {
        super();
        this.tax = tax;
    }

    public Marketing() {
        super();
        this.tax = 0.1;
    }

    @Override
    public double getTotalSalaryBudget() {
        double sum = 0;

        for (Employee emp : this.getEmployees()) {
            sum = sum + (1.0 * emp.getSalariu());
            if (emp.getSalariu() > 5000) {
                sum = sum - 0.1 * emp.getSalariu();
            } else if (emp.getSalariu() >= 3000) {
                sum = sum - 0.16 * emp.getSalariu();
            }
        }

        return sum;
    }

    @Override
    public String toString() {
        return "Marketing " + super.toString();
    }
}
