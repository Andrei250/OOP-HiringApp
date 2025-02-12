package com.pachetepachete.Models;

import com.pachetepachete.Exceptions.InvalidDatesException;
import com.pachetepachete.utils.Xerox;

import java.util.Date;
/*
    Entitatea Education care este de tip comparable si
    foloseste prototype pattern pentru a copia.
 */

public class Education extends Xerox implements Comparable<Education> {
    private Date start;
    private Date end;
    private String institutie;
    private String nivelEducatie;
    private Double medie;

    public Education(Date start, Date end, String institutie, String nivelEducatie, Double medie) throws InvalidDatesException {
        try {
            if ((end != null && end.before(start)) || start == null) {
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

    public Education(Education education) {
        this.start = education.start;
        this.end = education.end;
        this.institutie = education.institutie;
        this.nivelEducatie = education.nivelEducatie;
        this.medie = education.medie;
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
        } else if (this.end != null) {
            return -1;
        } else if (o.end != null) {
            return 1;
        }

        return this.start.compareTo(o.start);
    }

    @Override
    public String toString() {
        return "Education incepand de la" +
                " " + start +
                " pana la " + end +
                " din institutia " + institutie +
                " cu  nivelul de educatie=" + nivelEducatie +
                " si media " + medie +
                '\n';
    }

    @Override
    public Education copy() {
        return new Education(this);
    }
}
