package com.pachetepachete;

import com.pachetepachete.Models.Information;

import java.util.Date;

public class Test {
    public static void main(String[] args) {
        Information info = new Information("nume", "prenume", "mail", "telefon", new Date(), "MASCULIN");
        System.out.println(info.addNewLanguage("ENGLISH", "BEGINNER"));
        System.out.println(info.addNewLanguage("ENGLISH", "BEGINNER"));
        System.out.println(info.addNewLanguage("da", "2"));
        System.out.println(info.addNewLanguage("da", "2"));
        System.out.println(info.addNewLanguage("ea", "3"));
        System.out.println(info.addNewLanguage("gdf", "2"));
        System.out.println(info.addNewLanguage("fdsa", "3"));
        System.out.println(info.addNewLanguage("vd", "3"));
        System.out.println(info.addNewLanguage("fda", "3"));

        System.out.println(info.getLanguages());

        System.out.println(info.modifyLanguage("ENGLISH", "2"));
        System.out.println(info.modifyLanguage("ENGLISH", "2"));
        System.out.println(info.modifyLanguage("da", "21"));
        System.out.println(info.modifyLanguage("da", "21"));
        System.out.println(info.modifyLanguage("ea", "31"));
        System.out.println(info.modifyLanguage("gdf", "21"));
        System.out.println(info.modifyLanguage("fdsa", "31"));
        System.out.println(info.modifyLanguage("vd", "31"));
        System.out.println(info.modifyLanguage("fda", "31"));

        System.out.println(info.getLanguages());

        System.out.println(info.removeLanguage("fdsa"));
        System.out.println(info.removeLanguage("vdfdas"));
        System.out.println(info.removeLanguage("fdfdasa"));

        System.out.println(info.getLanguages());
    }
}
