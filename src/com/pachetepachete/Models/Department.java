package com.pachetepachete.Models;

import java.util.ArrayList;

public abstract class Department {
    private ArrayList<Employee> employees;
    private ArrayList<Job> availableJobs;

    public Department() {
        this.employees = new ArrayList<>();
        this.availableJobs = new ArrayList<>();
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public ArrayList<Job> getJobs() {
        return availableJobs;
    }

    public void setAvailableJobs(ArrayList<Job> availableJobs) {
        this.availableJobs = availableJobs;
    }

    public abstract double getTotalSalaryBudget();

    public void add(Employee employee) {
        if (!this.employees.contains(employee)) {
            this.employees.add(employee);
        }
    }

    public void remove(Employee employee) {
        this.employees.remove(employee);
    }

    public void add(Job job) {
        if (!this.availableJobs.contains(job)) {
            this.availableJobs.add(job);
        }
    }

    public void remove(Job job) {
        this.availableJobs.remove(job);
    }

    @Override
    public String toString() {
        return "Departmentul cu " +
                "employees: \n" + employees +
                "si joburile valabile: \n" + availableJobs +
                '\n';
    }
}
