package com.pachetepachete.Models;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class User extends Consumer {
    public User() {
        super();
    }

    public Employee convert() {
        return new Employee(this, null, 0);
    }

    public Double getTotalScore() {
        int exp = 0;
        Calendar calendar = Calendar.getInstance();
        exp = getYearOfExperience(exp, calendar);

        double GPA = this.meanGPA();

        return exp * 1.5 + GPA;
    }

    public int getYearOfExperience(int exp, Calendar calendar) {
        for (Experience experience : this.getResume().getExperiences()) {
            if (experience.getEnd() == null) {
                if (!experience.getStart().after(new Date())) {
                    exp = getExp(exp, calendar, experience, new Date());
                }
            } else {
                exp = getExp(exp, calendar, experience, experience.getEnd());
            }
        }
        return exp;
    }

    private int getExp(int exp, Calendar calendar, Experience experience, Date end) {
        calendar.setTime(end);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);

        calendar.setTime(experience.getStart());
        year -= calendar.get(Calendar.YEAR);
        month -= calendar.get(Calendar.MONTH);

        int ans = year * 12 + month;
        if (ans % 12 != 0) {
            exp ++;
        }

        exp += (ans / 12);
        return exp;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
