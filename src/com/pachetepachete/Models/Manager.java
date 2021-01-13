package com.pachetepachete.Models;

import com.pachetepachete.Application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Manager extends Employee {
    private ArrayList<Request<Job, Consumer>> requests;

    public Manager(String companie, int salariu) {
        super(companie, salariu);
        this.requests = new ArrayList<>();
    }

    public Manager(Consumer consumer, String companie, int salariu) {
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

    public Manager(String companie) {
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
            Collections.sort(this.requests);
        }
    }

    public void remove(Request<Job, Consumer> request) {
        this.requests.remove(request);
    }

    public void process(Job job) {
        Collections.sort(this.requests);

        for (int i = 0; i < requests.size(); ++i) {
            if (requests.get(i).getKey() == job) {
                if (job.isOpened()) {
                    if (job.getNoPositions() != 0) {
                        if (Application.getInstance().contains((User) requests.get(i).getValue1())) {
                            Employee employee = ((User) requests.get(i).getValue1()).convert();
                            employee.setSalariu(job.getSalary());
                            Company cmp = Application.getInstance().getCompany(this.getCompanie());
                            cmp.add(employee, job.findDepartment());
                            Application.getInstance().remove((User) requests.get(i).getValue1());
                            job.setNoPositions(job.getNoPositions() - 1);
                            job.notifyOneObserver((User) requests.get(i).getValue1(), "Felicitari, ai fost acceptat pentru jobul " + job.getName());

                            for (Job job1 : ((User) requests.get(i).getValue1()).getJobs()) {
                                job1.dettach((User) requests.get(i).getValue1());
                            }

                            if (job.getNoPositions() == 0) {
                                job.setOpened(false);
                            }
                        }
                    } else {
                        job.setOpened(false);
                    }
                }

                this.requests.remove(requests.get(i));
                i--;
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
