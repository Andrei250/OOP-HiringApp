package com.pachetepachete;

import com.pachetepachete.Controllers.ApplicationController;
import com.pachetepachete.Exceptions.InvalidDatesException;
import com.pachetepachete.Exceptions.NoRecruitersException;
import com.pachetepachete.Models.*;
import com.pachetepachete.Views.ManagerPage;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TryApp {

    public static void main(String[] args) throws ParseException, InvalidDatesException, NoRecruitersException {
        Application application = Application.getInstance();

//        User user1 = new User();
//        User user2 = new User();
//        User user3 = new User();
//        User user4 = new User();
//        User user5 = new User();
//        User user6 = new User();
//        User user7 = new User();
//
//        application.add(user1);
//        application.add(user2);
//        application.add(user3);
//        application.add(user4);
//        application.add(user5);
//        application.add(user6);
//        application.add(user7);
//
//        try {
//            createAndAddInfo("Eusebiu", "Georgel", "gege@gmail.com", "0777777777", "21/05/2000", "MASCULIN", user1);
//            Education education1 = new Education(new SimpleDateFormat("dd/MM/yyyy").parse("21/09/2020"), null, "UPB", "Bachelor", null);
//            Experience experience1 = new Experience(new SimpleDateFormat("dd/MM/yyyy").parse("21/05/2020"), new SimpleDateFormat("dd/MM/yyyy").parse("21/08/2020"), "Software Developer", "ADOBE");
//            user1.add(education1);
//            user1.add(experience1);
//
//            createAndAddInfo("Eu", "Georgel", "ge32ge@gmail.com", "0777777777", "21/05/2000", "MASCULIN", user2);
//            Education education2 = new Education(new SimpleDateFormat("dd/MM/yyyy").parse("21/09/2016"), new SimpleDateFormat("dd/MM/yyyy").parse("21/09/2020"), "UPB", "Bachelor", 9.99);
//            Experience experience2 = new Experience(new SimpleDateFormat("dd/MM/yyyy").parse("21/05/2020"), new SimpleDateFormat("dd/MM/yyyy").parse("21/08/2020"), "Software Developer", "ADOBE");
//            user2.add(education2);
//            user2.add(experience2);
//
//            createAndAddInfo("Se", "Georgel", "ge432ge@gmail.com", "0777777777", "21/05/2000", "MASCULIN", user3);
//            Education education3 = new Education(new SimpleDateFormat("dd/MM/yyyy").parse("21/09/2020"), null, "UPB", "Bachelor", null);
//            Experience experience3 = new Experience(new SimpleDateFormat("dd/MM/yyyy").parse("21/05/2020"), new SimpleDateFormat("dd/MM/yyyy").parse("21/08/2020"), "Software Developer", "ADOBE");
//            user3.add(education3);
//            user3.add(experience3);
//
//            createAndAddInfo("Viu", "Georgel", "ge32ge@gmail.com", "0777777777", "21/05/2000", "MASCULIN", user4);
//            Education education4 = new Education(new SimpleDateFormat("dd/MM/yyyy").parse("21/09/2020"), null, "UPB", "Bachelor", null);
//            Experience experience4 = new Experience(new SimpleDateFormat("dd/MM/yyyy").parse("21/05/2020"), new SimpleDateFormat("dd/MM/yyyy").parse("21/08/2020"), "Software Developer", "ADOBE");
//            user4.add(education4);
//            user4.add(experience4);
//
//            createAndAddInfo("Ge", "Georgel", "ge5ge@gmail.com", "0777777777", "21/05/2000", "MASCULIN", user5);
//            Education education5 = new Education(new SimpleDateFormat("dd/MM/yyyy").parse("21/09/2020"), null, "UPB", "Bachelor", null);
//            Experience experience5 = new Experience(new SimpleDateFormat("dd/MM/yyyy").parse("21/05/2020"), new SimpleDateFormat("dd/MM/yyyy").parse("21/08/2020"), "Software Developer", "ADOBE");
//            user5.add(education5);
//            user5.add(experience5);
//
//            createAndAddInfo("OR", "Georgel", "ge3ge@gmail.com", "0777777777", "21/05/2000", "MASCULIN", user6);
//            Education education6 = new Education(new SimpleDateFormat("dd/MM/yyyy").parse("21/09/2016"), new SimpleDateFormat("dd/MM/yyyy").parse("21/09/2020"), "UPB", "Bachelor", null);
//            Experience experience6 = new Experience(new SimpleDateFormat("dd/MM/yyyy").parse("21/05/2020"), new SimpleDateFormat("dd/MM/yyyy").parse("21/07/2020"), "Software Developer", "ADOBE");
//            user6.add(education6);
//            user6.add(experience6);
//
//            createAndAddInfo("GEL", "GG", "geg2e@gmail.com", "0777777777", "21/05/2000", "MASCULIN", user7);
//            Education education7 = new Education(new SimpleDateFormat("dd/MM/yyyy").parse("21/09/2020"), null, "UPB", "Bachelor", null);
//            Experience experience7 = new Experience(new SimpleDateFormat("dd/MM/yyyy").parse("21/04/2020"), new SimpleDateFormat("dd/MM/yyyy").parse("21/08/2020"), "Software Developer", "ADOBE");
//            user7.add(education7);
//            user7.add(experience7);
//
//        } catch (ParseException | InvalidDatesException e) {
//            e.printStackTrace();
//        }
//
//
//        application.readFromFile("./src/com/pachetepachete/Input/companies.txt");

//        AdminPanel adminPanel = new AdminPanel();
//        ProfilePage profilePage = new ProfilePage();
        Test.main(new String[]{});
//        ManagerPage managerPage = new ManagerPage(application.getCompany("Amazon").getManager());
        new ApplicationController();
    }

    public static void createAndAddInfo(String name, String prenume, String email, String phone, String date, String sex, User user) throws ParseException {
        Information info1 = new Information(name, prenume, email, phone, new SimpleDateFormat("dd/MM/yyyy").parse(date), sex);
        Consumer.Resume resume1 = new Consumer.Resume.ResumeBuilder().information(info1).build();
        user.setResume(resume1);
    }
}
