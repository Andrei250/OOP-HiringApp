package com.pachetepachete.Models;

import com.pachetepachete.Application;
import com.pachetepachete.Exceptions.NoRecruitersException;
import com.pachetepachete.utils.Subject;

import java.util.ArrayList;
import java.util.Calendar;

//TODO: numele companiei si departamentul
public class Job extends Subject {
    private String name;
    private String company;
    private boolean isOpened;
    private ArrayList<User> persons;
    private int noPositions;
    private int salary;
    private ArrayList<Constraint> constraints;
    private String department;

    public Job(String name,
               String company,
               boolean isOpened,
               int noPositions,
               int salary,
               ArrayList<Constraint> constraints,
               String department) {
        super();
        this.name = name;
        this.company = company;
        this.isOpened = isOpened;
        this.persons = new ArrayList<>();
        this.noPositions = noPositions;
        this.salary = salary;
        this.constraints = constraints;
        this.department = department;
    }

    public Job(String name,
               String company,
               boolean isOpened,
               int noPositions,
               int salary,
               ArrayList<Constraint> constraints) {
        super();
        this.name = name;
        this.company = company;
        this.isOpened = isOpened;
        this.noPositions = noPositions;
        this.salary = salary;
        this.constraints = constraints;
        this.persons = new ArrayList<>();
    }

    public Job(String name,
               String company,
               boolean isOpened,
               int noPositions,
               int salary) {
        super();
        this.name = name;
        this.company = company;
        this.isOpened = isOpened;
        this.noPositions = noPositions;
        this.salary = salary;
        this.persons = new ArrayList<>();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;

        if (!opened) {
            notifyAllObserverOfCanceling("Jobul " + this.getName() + " s-a inchis. Daca se va schimba ceva, te vom anunta!");
        } else {
            notifyAllObserverOfCanceling("Jobul " + this.getName() + " s-a deschis. Aplica cat de repede poti!");
        }
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

    public Company getCompanyByName() {
        return Application.getInstance().findByName(company);
    }

    public Department findDepartment() {
        Company cmp = getCompanyByName();

        if (cmp != null && cmp.getDepartments() != null) {
            for (Department department1 : cmp.getDepartments()) {
                if (department.equalsIgnoreCase("IT") && department1 instanceof IT) {
                    return department1;
                } else if (department.equalsIgnoreCase("Finance") && department1 instanceof Finance) {
                    return department1;
                } else if (department.equalsIgnoreCase("Marketing") && department1 instanceof Marketing) {
                    return department1;
                } else if (department.equalsIgnoreCase("Management") && department1 instanceof Management) {
                    return department1;
                }
            }
        }

        return null;
    }

    public boolean add(User user) {
        if (!this.persons.contains(user)) {
            this.attach(user);
            user.add(this);
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
        Company cmp = Application.getInstance().findByName(company);

        Recruiter recruiter = cmp.getRecruiter(user);

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

    @Override
    public String toString() {
        return "Jobul cu numele " + name +
                " din compania " + company +
                " care deschis/inchis: " + isOpened +
                " cu persoanele: \n" + persons +
                "\n  cu " + noPositions +
                " pozitii libere si salariul " + salary +
                " si constrangerile: \n" + constraints +
                '\n';
    }
}
