//package com.pachetepachete;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Application {
//    private ArrayList<Company> companies;
//    private ArrayList<User> users;
//    private static Application application;
//
//    private Application() {
//        this.companies = new ArrayList<Company>();
//        this.users = new ArrayList<User>();
//    }
//
//    public static Application getInstance() {
//        if (application == null) {
//            synchronized (Application.class) {
//                if (application == null) {
//                    application = new Application();
//                }
//            }
//        }
//
//        return application;
//    }
//
//    public ArrayList<Company> getCompanies() {
//        return companies;
//    }
//
//    public void setCompanies(ArrayList<Company> companies) {
//        this.companies = companies;
//    }
//
//    public ArrayList<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(ArrayList<User> users) {
//        this.users = users;
//    }
//
//    public void add(Company company) {
//        if (!this.companies.contains(company)) {
//            this.companies.add(company);
//        }
//    }
//
//    public void add(User user) {
//        if (!this.users.contains(user)) {
//            this.users.add(user);
//        }
//    }
//
//    public boolean remove(Company company) {
//        if (this.companies.contains(company)) {
//            this.companies.remove(company);
//            return true;
//        }
//
//        return false;
//    }
//
//    public boolean remove(User user) {
//        if (this.users.contains(user)) {
//            this.users.remove(user);
//            return true;
//        }
//
//        return false;
//    }
//
//    public ArrayList<Job> getJobs(List<String> companies) {
//
//    }
//}
