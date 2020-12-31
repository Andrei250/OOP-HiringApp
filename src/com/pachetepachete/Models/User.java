package com.pachetepachete.Models;

import com.pachetepachete.utils.ObserverJob;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class User extends Consumer implements ObserverJob {
    private ArrayList<Job> jobs;
    private ArrayList<String> following;

    public User() {
        super();
        this.jobs = new ArrayList<>();
        this.following = new ArrayList<>();
    }

    public User(Consumer consumer) {
        super(consumer);
        this.jobs = new ArrayList<>();
        this.following = new ArrayList<>();
    }

    public ArrayList<String> getFollowing() {
        return following;
    }

    public void setFollowing(ArrayList<String> following) {
        this.following = following;
    }

    @Override
    public void update(String message) {
        this.addNotification(new Notification(message));
    }

    public Employee convert() {
        return new Employee(this, null, 0);
    }

    public ArrayList<Job> getJobs() {
        return jobs;
    }

    public void setJobs(ArrayList<Job> jobs) {
        this.jobs = jobs;
    }

    public void add(Job job) {
        if (!this.jobs.contains(job)) {
            this.jobs.add(job);
        }
    }

    public Double getTotalScore() {
        int exp = 0;
        Calendar calendar = Calendar.getInstance();
        exp = getYearOfExperience(exp, calendar);

        double GPA = this.meanGPA();

        return exp * 1.5 + GPA;
    }

    public int getYearOfExperience(int exp, Calendar calendar) {
        for (Experience experience : this.getResume().getExperiences()) {
            if (experience.getEnd() == null) {
                if (!experience.getStart().after(new Date())) {
                    exp = getExp(exp, calendar, experience, new Date());
                }
            } else {
                exp = getExp(exp, calendar, experience, experience.getEnd());
            }
        }
        return exp;
    }

    private int getExp(int exp, Calendar calendar, Experience experience, Date end) {
        calendar.setTime(end);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);

        calendar.setTime(experience.getStart());
        year -= calendar.get(Calendar.YEAR);
        month -= calendar.get(Calendar.MONTH);

        int ans = year * 12 + month;
        if (ans % 12 != 0) {
            exp ++;
        }

        exp += (ans / 12);
        return exp;
    }

    @Override
    public String toString() {
        if (this.getResume() != null && this.getResume().getInformation() != null) {
            return this.getResume().getInformation().getFullname();
        }

        return "";
    }
}
