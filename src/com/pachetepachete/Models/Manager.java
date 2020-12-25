package com.pachetepachete.Models;

import java.util.ArrayList;

public class Manager extends Employee {
    private ArrayList<Request<Job, Consumer>> requests;

    public Manager(Company companie, int salariu) {
        super(companie, salariu);
        this.requests = new ArrayList<>();
    }

    public Manager(Consumer consumer, Company companie, int salariu) {
        super(consumer, companie, salariu);
        this.requests = new ArrayList<>();
    }

    public Manager() {
        super();
        this.requests = new ArrayList<>();
    }

    public Manager(int salariu) {
        super(salariu);
        this.requests = new ArrayList<>();
    }

    public Manager(Company companie) {
        super(companie);
        this.requests = new ArrayList<>();
    }

    public ArrayList<Request<Job, Consumer>> getRequests() {
        return requests;
    }

    public void setRequests(ArrayList<Request<Job, Consumer>> requests) {
        this.requests = requests;
    }

    public void add(Request<Job, Consumer> request) {
        if (!this.requests.contains(request)) {
            this.requests.add(request);
        }
    }

    //TODO: De terminat
    public void process(Job job) {

    }
}
