package com.pachetepachete.Models;

import com.pachetepachete.Application;
import com.pachetepachete.Exceptions.NoRecruitersException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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

            if (getDepartmentByName("IT") == null) {
                this.add(new IT());
            }

            getDepartmentByName("IT").add(recruiter);
        }
    }

    public void add(Employee employee, Department department) {
        if (department == null) {
            return;
        }

        employee.setCompanie(this.getName());

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
        Collections.sort(this.recruiters);
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

    public ArrayList<Job> getJobs() {
        ArrayList<Job> ans = new ArrayList<>();

        for (Department department : this.departments) {
            for (Job job : department.getJobs()) {
                if (job.isOpened()) {
                    ans.add(job);
                }
            }
        }

        return ans;
    }

    public Recruiter getRecruiter(User user) throws NoRecruitersException {
        int lvl = -1;
        Recruiter recruiter = null;

        try {
            if (this.recruiters.size() == 0) {
                throw new NoRecruitersException("Aceasta companie nu are inca recruiteri. Ne pare rau!");
            }
        } catch(NoRecruitersException e) {
            e.printStackTrace();
            return null;
        }


        for (Recruiter recruiter1 : this.recruiters) {
            int degree = user.getDegreeInFriendship(recruiter1);

            if (degree > lvl) {
                lvl = degree;
                recruiter = recruiter1;
            } else if (degree == lvl && degree != -1) {
                if (recruiter.rating < recruiter1.rating) {
                    recruiter = recruiter1;
                }
            }
        }

        if (lvl == -1) {
            recruiter = this.recruiters.get(0);
        }

        return recruiter;
    }

    public Department getDepartmentByName(String name) {
        return Application.getIfDepartmentExists(name, this.getDepartments());
    }

    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder();

        ans.append(name).append("\n");
        ans.append("Manager: ").append(manager.getResume().getInformation().getFullname()).append("\n");

        ans.append("Departamente: \n");

        for (Department department : departments) {
            ans.append("\t").append(department.toString()).append("\n");
        }

        ans.append("Recruiters: \n");

        for (Recruiter recruiter : recruiters) {
            ans.append("\t").append(recruiter.getResume().getInformation().getFullname()).append("\n");
        }

        return ans.toString();
    }
}
