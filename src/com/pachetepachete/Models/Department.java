package com.pachetepachete.Models;

import java.util.ArrayList;
/*
    Entitatea abstracta Departament.
 */
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

    /*
        Cauta Job dupa nume.
     */
    public Job findJobByName(String name) {
        for (Job job : getJobs()) {
            if (job.getName().equalsIgnoreCase(name)) {
                return job;
            }
        }

        return null;
    }

    public void remove(Job job) {
        this.availableJobs.remove(job);
    }

    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder();

        ans.append(getClass().getSimpleName()).append(" ");

        ans.append("Joburi: \n");

        for (Job job : availableJobs) {
            ans.append("\t").append(job.getName()).append("\n");
        }

        ans.append("Angajati: \n");

        for (Employee employee : employees) {
            ans.append("\t").append(employee.getResume().getInformation().getFullname()).append("\n");
        }

        return ans.toString();
    }
}
