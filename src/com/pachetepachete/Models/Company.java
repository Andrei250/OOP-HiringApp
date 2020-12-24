package com.pachetepachete.Models;

import java.util.ArrayList;

public class Company {
    private String name;
    private Manager manager;
    private ArrayList<Department> departments;
    private ArrayList<Recruiter> recruiters;

    public Company(String name, Manager manager) {
        this.name = name;
        this.manager = manager;
        this.departments = new ArrayList<Department>();
        this.recruiters = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(ArrayList<Department> departments) {
        this.departments = departments;
    }

    public ArrayList<Recruiter> getRecruiters() {
        return recruiters;
    }

    public void setRecruiters(ArrayList<Recruiter> recruiters) {
        this.recruiters = recruiters;
    }

    public void add(Department department) {
        if (!this.departments.contains(department)) {
            this.departments.add(department);
        }
    }

    public void add(Recruiter recruiter) {
        if (!this.recruiters.contains(recruiter)) {
            this.recruiters.add(recruiter);
        }
    }

    public void add(Employee employee, Department department) {
        if (!this.departments.contains(department)) {
            return;
        }

        department.add(employee);
    }

    public void remove(Employee employee) {
        for (Department department : this.departments) {
            department.getEmployees().remove(employee);
        }
    }

    public void remove(Department department) {
        for (Employee employee : department.getEmployees()) {
            this.recruiters.remove((Recruiter) employee);
        }

        department.getEmployees().clear();

        this.departments.remove(department);
    }

    public void remove(Recruiter recruiter) {
        this.recruiters.remove(recruiter);
    }

    public void move(Department source, Department destination) {
        if (!this.departments.contains(destination)) {
            add(destination);
        }

        for (Job job : source.getJobs()) {
            destination.add(job);
        }

        for (Employee employee : source.getEmployees()) {
            destination.add(employee);
        }

        remove(source);
    }

    public void move(Employee employee, Department newDepartment) {
        if (!this.departments.contains(newDepartment)) {
            return;
        }

        if (!newDepartment.getEmployees().contains(employee)) {
            for (Department department : this.getDepartments()) {
                if (department.getEmployees().contains(employee)) {
                    department.remove(employee);
                }
            }

            newDepartment.add(employee);
        }
    }

    public boolean contains(Department department) {
        return this.getDepartments().contains(department);
    }

    public boolean contains(Employee employee) {
        boolean check = false;

        for (Department department : this.getDepartments()) {
            if (department.getEmployees().contains(employee)) {
                check = true;
                break;
            }
        }

        return check;
    }

    public boolean contains(Recruiter recruiter) {
        return this.recruiters.contains(recruiter);
    }


}
