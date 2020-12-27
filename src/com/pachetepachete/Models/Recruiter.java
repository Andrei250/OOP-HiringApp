package com.pachetepachete.Models;

public class Recruiter extends Employee implements Comparable<Recruiter> {
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

    public int evaluate(Job job, User user) {
        this.rating  = this.rating + 0.1;

        if (!job.meetsRequirments(user)) {
            return 0;
        }
        Company company = job.getCompanyByName();
        if (company != null) {
            company.getManager().add(new Request<Job, Consumer>(job, user, this, user.getTotalScore()));
            return 1;
        }

        return 0;
    }

    @Override
    public int compareTo(Recruiter o) {
        return o.rating.compareTo(this.rating);
    }

    @Override
    public String toString() {
        return "Recruiter " + super.toString() +
                " cu raitingul " + rating +
                ".\n";
    }
}
