package com.pachetepachete.Models;

public class Employee extends Consumer {
    private Company companie;
    private int salariu;

    public Employee(Company companie, int salariu) {
        super();
        this.companie = companie;
        this.salariu = salariu;
    }

    public Employee(Consumer consumer, Company companie, int salariu) {
        super(consumer);
        this.companie = companie;
        this.salariu = salariu;
    }

    public Employee() {
        super();
    }

    public Employee(int salariu) {
        super();
        this.salariu = salariu;
    }

    public Employee(Company companie) {
        super();
        this.companie = companie;
    }

    public Company getCompanie() {
        return companie;
    }

    public void setCompanie(Company companie) {
        this.companie = companie;
    }

    public int getSalariu() {
        return salariu;
    }

    public void setSalariu(int salariu) {
        this.salariu = salariu;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
