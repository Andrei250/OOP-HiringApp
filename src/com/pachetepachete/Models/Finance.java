package com.pachetepachete.Models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Finance extends Department {
    public Finance() {
        super();
    }

    @Override
    public double getTotalSalaryBudget() {
        double sum = 0;
        Calendar calendar = Calendar.getInstance();

        for (Employee employee : this.getEmployees()) {
            ArrayList<Experience> experiences = employee.getResume().getExperiences();
            Date start = experiences.get(experiences.size() - 1).getStart();
            calendar.setTime(new Date());
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);

            calendar.setTime(start);
            year -= calendar.get(Calendar.YEAR);
            month -= calendar.get(Calendar.MONTH);

            int value = year * 12 + month;

            if (value >= 12) {
                sum = sum + (1.0 * employee.getSalariu() - 0.16 * employee.getSalariu());
            } else {
                sum = sum + (1.0 * employee.getSalariu() - 0.1 * employee.getSalariu());
            }
        }

        return sum;
    }

    @Override
    public String toString() {
        return "Finance " + super.toString();
    }
}
