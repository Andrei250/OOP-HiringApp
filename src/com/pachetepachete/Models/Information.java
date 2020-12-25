package com.pachetepachete.Models;

import com.pachetepachete.utils.Pair;
import com.pachetepachete.utils.PairArray;

import java.util.ArrayList;
import java.util.Date;

public class Information {
    private String nume;
    private String prenume;
    private String email;
    private String telefon;
    private Date data_nastere;
    private String sex;
    private PairArray<String, String> languages;

    public Information(String nume, String prenume, String email, String telefon, Date data_nastere, String sex) {
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.telefon = telefon;
        this.data_nastere = data_nastere;
        this.sex = sex;
        this.languages = new PairArray<>();
    }

    public Information(String nume, String prenume, String email, String telefon, Date data_nastere, String sex, PairArray<String, String> languages) {
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.telefon = telefon;
        this.data_nastere = data_nastere;
        this.sex = sex;
        this.languages = languages;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Date getData_nastere() {
        return data_nastere;
    }

    public void setData_nastere(Date data_nastere) {
        this.data_nastere = data_nastere;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public ArrayList<Pair<String, String>> getLanguages() {
        return languages;
    }

    public void setLanguages(PairArray<String, String> languages) {
        this.languages = languages;
    }

    public boolean addNewLanguage(String language, String level) {
        if (!languages.containKey(language)) {
            return languages.add(new Pair<>(language, level));
        }

        return false;
    }

    public boolean removeLanguage(String language) {
        return languages.removeKey(language);
    }

    public boolean modifyLanguage(String language, String value) {
        return languages.updateKey(language, value);
    }

    @Override
    public String toString() {
        return "Information: \n" +
                " Numele: " + nume +
                "\n Prenumele: " + prenume +
                "\n Email: " + email +
                "\n Telefonul: " + telefon +
                "\n Data Nastere: " + data_nastere +
                "\n Sexul: " + sex +
                "\n Limbi cunoscute: " + languages +
                '\n';
    }
}
