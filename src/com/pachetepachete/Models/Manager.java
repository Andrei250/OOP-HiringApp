package com.pachetepachete.Models;

//TODO: de terminat
public class Manager extends Employee {
    public Manager(Company companie, int salariu) {
        super(companie, salariu);
    }

    public Manager(Consumer consumer, Company companie, int salariu) {
        super(consumer, companie, salariu);
    }

    public Manager() {
        super();
    }

    public Manager(int salariu) {
        super(salariu);
    }

    public Manager(Company companie) {
        super(companie);
    }

    public void process(Job job) {

    }
}
