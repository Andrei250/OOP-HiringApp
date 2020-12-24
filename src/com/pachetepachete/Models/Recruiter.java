package com.pachetepachete.Models;

public class Recruiter extends Employee{
    public Double rating;

    public Recruiter(String companie, int salariu, Double rating) {
        super(companie, salariu);
        this.rating = rating;
    }

    public Recruiter(Consumer consumer, String companie, int salariu, Double rating) {
        super(consumer, companie, salariu);
        this.rating = rating;
    }

    public Recruiter(String companie, int salariu) {
        super(companie, salariu);
        this.rating = 5.0;
    }


}
