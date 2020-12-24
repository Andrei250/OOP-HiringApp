package com.pachetepachete;

import com.pachetepachete.Exceptions.InvalidDatesException;
import com.pachetepachete.Models.*;

import java.util.Date;

public class Test {
    public static void main(String[] args) throws InvalidDatesException {
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
        System.out.println(info.getData_nastere());
        Education ed = new Education(new Date(12), new Date(41234123), "inst", "mare", 9.90);
        Education ed1 = new Education(new Date(), new Date(41234123), "inst", "mare", 9.90);
        Education ed3 = new Education(new Date(), new Date(), "inst", "mare", 9.90);
        Education ed2 = new Education(new Date(), new Date(), "inst", "mare", 9.90);
        Experience exp = new Experience(new Date(), new Date(), "Tester", "Companie");

        User user = new User();
        user.setResume(new Consumer.Resume.ResumeBuilder().information(info).build());
        user.add(ed);
        user.add(ed2);
        user.add(ed2);

        System.out.println(user.toString());

        Employee employee = user.convert();
        employee.add(ed3);
        employee.add(exp);

        System.out.println(employee.toString());
        System.out.println(employee.compare(employee, user));

    }
}
