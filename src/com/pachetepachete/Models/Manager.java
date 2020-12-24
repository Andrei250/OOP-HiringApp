package com.pachetepachete.Models;

public class Manager extends Employee {
    public Manager(String companie, int salariu) {
        super(companie, salariu);
    }

    public Manager(Consumer consumer, String companie, int salariu) {
        super(consumer, companie, salariu);
    }

    public Manager() {
        super();
    }

    public Manager(int salariu) {
        super(salariu);
    }

    public Manager(String companie) {
        super(companie);
    }

    public void process(Job job) {

    }
}
