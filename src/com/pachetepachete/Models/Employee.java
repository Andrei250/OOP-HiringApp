package com.pachetepachete.Models;

public class Employee extends Consumer {
    private String companie;
    private int salariu;

    public Employee(String companie, int salariu) {
        super();
        this.companie = companie;
        this.salariu = salariu;
    }

    public Employee(Consumer consumer, String companie, int salariu) {
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

    public Employee(String companie) {
        super();
        this.companie = companie;
    }

    public String getCompanie() {
        return companie;
    }

    public void setCompanie(String companie) {
        this.companie = companie;
    }

    public int getSalariu() {
        return salariu;
    }

    public void setSalariu(int salariu) {
        this.salariu = salariu;
    }

}
