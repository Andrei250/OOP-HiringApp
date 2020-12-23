package com.pachetepachete.Models;

import com.pachetepachete.Exceptions.InvalidDatesException;

import java.util.Date;

public class Education implements Comparable<Education> {
    private Date start;
    private Date end;
    private String institutie;
    private String nivelEducatie;
    private Double medie;

    public Education(Date start, Date end, String institutie, String nivelEducatie, Double medie) throws InvalidDatesException {
        try {
            if (end.before(start)) {
                throw new InvalidDatesException("Date de sfarsit este mai mica ca cea de inceput! INVALID!");
            }
        } catch (InvalidDatesException e) {
            e.printStackTrace();
        }

        this.start = start;
        this.end = end;
        this.institutie = institutie;
        this.nivelEducatie = nivelEducatie;
        this.medie = medie;
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

    public String getInstitutie() {
        return institutie;
    }

    public void setInstitutie(String institutie) {
        this.institutie = institutie;
    }

    public String getNivelEducatie() {
        return nivelEducatie;
    }

    public void setNivelEducatie(String nivelEducatie) {
        this.nivelEducatie = nivelEducatie;
    }

    public Double getMedie() {
        return medie;
    }

    public void setMedie(Double medie) {
        this.medie = medie;
    }

    @Override
    public int compareTo(Education o) {
        if (this.end != null && o.end != null) {
            if (this.end == o.end) {
                return o.medie.compareTo(this.medie);
            }

            return o.end.compareTo(this.end);
        }

        return this.start.compareTo(o.start);
    }

    @Override
    public String toString() {
        return "Education{" +
                "start=" + start +
                ", end=" + end +
                ", institutie='" + institutie + '\'' +
                ", nivelEducatie='" + nivelEducatie + '\'' +
                ", medie=" + medie +
                '}';
    }
}
