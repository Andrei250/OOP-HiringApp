package com.pachetepachete.Models;

public class Recruiter extends Employee {
    public Double rating;

    public Recruiter(Company companie, int salariu, Double rating) {
        super(companie, salariu);
        this.rating = rating;
    }

    public Recruiter(Consumer consumer, Company companie, int salariu, Double rating) {
        super(consumer, companie, salariu);
        this.rating = rating;
    }

    public Recruiter(Company companie, int salariu) {
        super(companie, salariu);
        this.rating = 5.0;
    }

    //TODO: CINE CACAT E SCORUL??? si trimite request-ul
    public int evaluate(Job job, User user) {
        this.rating  = this.rating + 0.1;

        new Request<Job, Consumer>(job, user, this, this.rating);

        return 1;
    }
}
