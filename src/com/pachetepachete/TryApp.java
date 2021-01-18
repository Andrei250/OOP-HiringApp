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
        LoadFiles.main(new String[]{});
        new ApplicationController();
    }

    public static void createAndAddInfo(String name, String prenume, String email, String phone, String date, String sex, User user) throws ParseException {
        Information info1 = new Information(name, prenume, email, phone, new SimpleDateFormat("dd/MM/yyyy").parse(date), sex);
        Consumer.Resume resume1 = new Consumer.Resume.ResumeBuilder().information(info1).build();
        user.setResume(resume1);
    }
}
