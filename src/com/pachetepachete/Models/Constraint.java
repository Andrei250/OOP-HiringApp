package com.pachetepachete.Models;

import java.util.Date;

/*
    Entitatea Contraint.
 */

public class Constraint {
    private Double start;
    private Double end;

    public Constraint(Double start, Double end) {
        this.start = start;
        this.end = end;
    }

    public Double getStart() {
        return start;
    }

    public void setStart(Double start) {
        this.start = start;
    }

    public Double getEnd() {
        return end;
    }

    public void setEnd(Double end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Contrangerea incepe de la " + start + " pana la " + end + ".\n";
    }
}
