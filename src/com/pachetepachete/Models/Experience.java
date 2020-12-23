package com.pachetepachete.Models;

import com.pachetepachete.Exceptions.InvalidDatesException;

import java.util.Date;

public class Experience implements Comparable<Experience> {
    private Date start;
    private Date end;
    private String pozitie;
    private String companie;

    public Experience(Date start, Date end, String pozitie, String companie) throws InvalidDatesException {
        try {
            if (end.before(start)) {
                throw new InvalidDatesException("Date de sfarsit este mai mica ca cea de inceput! INVALID!");
            }
        } catch (InvalidDatesException e) {
            e.printStackTrace();
        }

        this.start = start;
        this.end = end;
        this.pozitie = pozitie;
        this.companie = companie;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getPozitie() {
        return pozitie;
    }

    public void setPozitie(String pozitie) {
        this.pozitie = pozitie;
    }

    public String getCompanie() {
        return companie;
    }

    public void setCompanie(String companie) {
        this.companie = companie;
    }

    @Override
    public int compareTo(Experience o) {
        if (this.end != null && o.end != null) {
            if (this.end == o.end) {
                return this.companie.compareTo(o.companie);
            }

            return o.end.compareTo(this.end);
        }

        return this.start.compareTo(o.start);
    }

    @Override
    public String toString() {
        return "Experience{" +
                "start=" + start +
                ", end=" + end +
                ", pozitie='" + pozitie + '\'' +
                ", companie='" + companie + '\'' +
                '}';
    }
}
