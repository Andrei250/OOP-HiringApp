package com.pachetepachete.Models;

import com.pachetepachete.Exceptions.NoRecruitersException;

import java.util.ArrayList;
import java.util.Calendar;

public class Job {
    private String name;
    private Company company;
    private boolean isOpened;
    private ArrayList<User> persons;
    private int noPositions;
    private int salary;
    private ArrayList<Constraint> constraints;
    private Department department;

    public Job(String name,
               Company company,
               boolean isOpened,
               ArrayList<User> persons,
               int noPositions,
               int salary,
               ArrayList<Constraint> constraints,
               Department department) {
        this.name = name;
        this.company = company;
        this.isOpened = isOpened;
        this.persons = persons;
        this.noPositions = noPositions;
        this.salary = salary;
        this.constraints = constraints;
        this.department = department;
    }

    public Job(String name,
               Company company,
               boolean isOpened,
               int noPositions,
               int salary,
               ArrayList<Constraint> constraints) {
        this.name = name;
        this.company = company;
        this.isOpened = isOpened;
        this.noPositions = noPositions;
        this.salary = salary;
        this.constraints = constraints;
    }

    public Job(String name,
               Company company,
               boolean isOpened,
               int noPositions,
               int salary) {
        this.name = name;
        this.company = company;
        this.isOpened = isOpened;
        this.noPositions = noPositions;
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public ArrayList<User> getPersons() {
        return persons;
    }

    public void setPersons(ArrayList<User> persons) {
        this.persons = persons;
    }

    public int getNoPositions() {
        return noPositions;
    }

    public void setNoPositions(int noPositions) {
        this.noPositions = noPositions;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public ArrayList<Constraint> getConstraints() {
        return constraints;
    }

    public void setConstraints(ArrayList<Constraint> constraints) {
        this.constraints = constraints;
    }

    public boolean add(User user) {
        if (!this.persons.contains(user)) {
            return this.persons.add(user);
        }

        return false;
    }

    public boolean remove(User user) {
        if (this.persons.contains(user)) {
            return this.persons.remove(user);
        }

        return false;
    }

    public void apply(User user) throws NoRecruitersException {
        if (this.persons.contains(user)) {
            return;
        }

        Recruiter recruiter = company.getRecruiter(user);

        if (recruiter == null) {
            return;
        }

        recruiter.evaluate(this, user);
    }

    public boolean meetsRequirments(User user) {
        int educationEnd = user.getGraduationYear();
        int experienceYears = 0;
        Double GPA = user.meanGPA();

        experienceYears = user.getYearOfExperience(experienceYears, Calendar.getInstance());

        if (this.constraints.size() == 0) {
            return true;
        }

        if (this.constraints.get(0).getEnd() < educationEnd) {
            return false;
        }

        if (this.constraints.size() == 1) {
            return true;
        }

        if (this.constraints.get(1).getStart() > experienceYears ||
                this.constraints.get(1).getEnd() < experienceYears) {
            return false;
        }

        if (this.constraints.size() == 2) {
            return true;
        }

        return !(this.constraints.get(2).getStart() > experienceYears) &&
                !(this.constraints.get(2).getEnd() < experienceYears);
    }
}
