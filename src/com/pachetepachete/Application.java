package com.pachetepachete;

import com.pachetepachete.Exceptions.InvalidDatesException;
import com.pachetepachete.Models.*;
import com.pachetepachete.utils.DepartmentFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/*
    Entitatea aplicatie.
    Singleton, deoarece am avut nevoie de aceeasi instanta peste tot.
 */
public class Application {
    private ArrayList<Company> companies;
    private ArrayList<User> users;
    private static Application application;

    private Application() {
        this.companies = new ArrayList<Company>();
        this.users = new ArrayList<User>();
    }

    public static Application getInstance() {
        if (application == null) {
            synchronized (Application.class) {
                if (application == null) {
                    application = new Application();
                }
            }
        }

        return application;
    }

    public ArrayList<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(ArrayList<Company> companies) {
        this.companies = companies;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void add(Company company) {
        if (!this.companies.contains(company)) {
            this.companies.add(company);
        }
    }

    public void add(User user) {
        if (!this.users.contains(user)) {
            this.users.add(user);
        }
    }

    public boolean remove(Company company) {
        if (this.companies.contains(company)) {
            this.companies.remove(company);
            return true;
        }

        return false;
    }

    public boolean remove(User user) {
        if (this.users.contains(user)) {
            this.users.remove(user);
            return true;
        }

        return false;
    }

    public boolean contains(User user) {
        return this.users.contains(user);
    }

    public User findByEmail(String email) {
        for (User user : this.users) {
            if (user.getResume() != null && user.getResume().getInformation() != null) {
                if (user.getResume().getInformation().getEmail().equals(email)) {
                    return user;
                }
            }
        }

        return null;
    }

    public Company getCompany(String name) {
        for (Company company : this.companies) {
            if (company.getName().compareTo(name) == 0) {
                return company;
            }
        }

        return null;
    }

    public ArrayList<Job> getJobs(List<String> companies) {
        ArrayList<Job> ans = new ArrayList<>();

        for (String st : companies) {
            Company company = getCompany(st);
            if (company != null) {
                ans.addAll(company.getJobs());
            }
        }

        return ans;
    }

    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder();

        ans.append("Applciation:\n");
        ans.append("\tCompanies: \n");

        for (Company company : this.getCompanies()) {
            ans.append("\t-").append(company.toString()).append("\n");
        }

        ans.append("\tUsers: \n");

        for (User user : this.getUsers()) {
            ans.append("\t").append(user.getResume().getInformation().getFullname()).append("\n");
        }

        return ans.toString();
    }

    //Ia numele departamentului
    public String getDepartmentName(Department department) {
        if (department instanceof IT) {
            return "IT";
        } else if (department instanceof Finance) {
            return "Finance";
        } else if (department instanceof Marketing) {
            return "Marketing";
        } else if (department instanceof Management) {
            return "Management";
        }
        return "";
    }

    //Cauta un Consumer dupa email.
    public Consumer getConsumerByEmail(String email) {
        for (User user : this.getUsers()) {
            if (user.getResume().getInformation().getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }

        for (Company company : this.getCompanies()) {
            for (Recruiter recruiter : company.getRecruiters()) {
                if (recruiter.getResume().getInformation().getEmail().equalsIgnoreCase(email)) {
                    return recruiter;
                }
            }

            for (Department department : company.getDepartments()) {
                for (Employee employee : department.getEmployees()) {
                    if (employee.getResume().getInformation().getEmail().equalsIgnoreCase(email)) {
                        return employee;
                    }
                }
            }
        }

        return null;
    }

    //verifica daca exista un departament dupa nume. Metoda statica, folosita peste tot.
    public static Department getIfDepartmentExists(String departmentName, ArrayList<Department> departments) {
        for (Department department : departments) {
            if (departmentName.equalsIgnoreCase("IT") && department instanceof IT) {
                return department;
            } else if (departmentName.equalsIgnoreCase("Finance") && department instanceof Finance) {
                return department;
            } else if (departmentName.equalsIgnoreCase("Marketing") && department instanceof Marketing) {
                return department;
            } else if (departmentName.equalsIgnoreCase("Management") && department instanceof Management) {
                return department;
            }
        }

        return null;
    }

}
