package com.pachetepachete.Models;

import com.pachetepachete.Application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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

    public void process(Job job) {
        Collections.sort(this.requests);

        for (Request<Job, Consumer> request : this.requests) {
            if (request.getKey() == job) {
                if (job.isOpened()) {
                    if (job.getNoPositions() != 0) {
                        if (Application.getInstance().contains((User) request.getValue1())) {
                            Employee employee = ((User) request.getValue1()).convert();
                            employee.setSalariu(job.getSalary());
                            this.getCompanie().add(employee, job.getDepartment());
                            Application.getInstance().remove((User) request.getValue1());
                            job.setNoPositions(job.getNoPositions() - 1);
                            job.notifyOneObserver((User) request.getValue1(), "Felicitari, ai fost acceptat pentru jobul " + job.getName());

                            for (Job job1 : ((User) request.getValue1()).getJobs()) {
                                job1.dettach((User) request.getValue1());
                            }

                            if (job.getNoPositions() == 0) {
                                job.setOpened(false);
                            }
                        }
                    } else {
                        job.setOpened(false);
                    }
                }

                this.requests.remove(request);
            }
        }

        job.setOpened(false);
    }

    @Override
    public String toString() {
        return "Manager " + super.toString() +
                "\n cu cererile " + requests +
                '\n';
    }
}
