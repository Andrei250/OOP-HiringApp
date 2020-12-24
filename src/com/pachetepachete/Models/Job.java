package com.pachetepachete.Models;

import java.util.ArrayList;

//TODO: De terminat
public class Job {
    private String name;
    private Company company;
    private boolean isOpened;
    private ArrayList<User> persons;
    private int noPositions;
    private int salary;
    private ArrayList<Constraint> constraints;

    public Job(String name, Company company, boolean isOpened, int noPositions, int salary, ArrayList<Constraint> constraints) {
        this.name = name;
        this.company = company;
        this.isOpened = isOpened;
        this.noPositions = noPositions;
        this.salary = salary;
        this.constraints = constraints;
    }

    public Job(String name, Company company, boolean isOpened, int noPositions, int salary) {
        this.name = name;
        this.company = company;
        this.isOpened = isOpened;
        this.noPositions = noPositions;
        this.salary = salary;
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
            return this.remove(user);
        }

        return false;
    }

}
