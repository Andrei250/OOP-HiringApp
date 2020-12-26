package com.pachetepachete.Models;

import com.pachetepachete.Exceptions.InvalidDatesException;
import com.pachetepachete.utils.Xerox;

import java.util.Date;

public class Experience extends Xerox implements Comparable<Experience> {
    private Date start;
    private Date end;
    private String pozitie;
    private String companie;

    public Experience(Date start, Date end, String pozitie, String companie) throws InvalidDatesException {
        try {
            if ((end != null && end.before(start)) || start == null) {
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

    public Experience(Experience experience) {
        this.end = experience.end;
        this.start = experience.start;
        this.companie = experience.companie;
        this.pozitie = experience.pozitie;
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
        } else if (this.end != null) {
            return -1;
        } else if (o.end != null) {
            return 1;
        }

        return this.start.compareTo(o.start);
    }

    @Override
    public String toString() {
        return "Experienta de la: " + start +
                " pana la end: " + end +
                " ocupand pozitia de: " + pozitie +
                " din compania: " + companie +
                '\n';
    }

    @Override
    public Experience copy() {
        return new Experience(this);
    }
}
