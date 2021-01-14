package com.pachetepachete.Models;

/*
    Entitatea Employee
 */
public class Employee extends Consumer {
    private String companie;
    private int salariu;
    private String department;

    public Employee(String companie, int salariu, String department) {
        super();
        this.companie = companie;
        this.salariu = salariu;
        this.department = department;
    }

    public Employee(String companie, int salariu) {
        super();
        this.companie = companie;
        this.salariu = salariu;
    }

    //Constructor de copiere
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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

    @Override
    public String toString() {
        return "Angajatul din compania " + companie +
                " si cu ca salariul " + salariu +
                " si resumeul " + this.getResume() +
                ",\n si prietenii: \n" + this.getFriends() +
                "\n";
    }
}
