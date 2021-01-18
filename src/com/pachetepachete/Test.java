package com.pachetepachete;

import com.pachetepachete.Controllers.ReadController;
import com.pachetepachete.Exceptions.InvalidDatesException;
import com.pachetepachete.Exceptions.NoRecruitersException;
import com.pachetepachete.Models.*;

public class Test {
    public static void main(String[] args) throws InvalidDatesException, NoRecruitersException {
        Application application = Application.getInstance();

        LoadFiles.main(new String[]{});

        Company google = application.getCompany("Google");
        Company amazon = application.getCompany("Amazon");

        for (Job job : google.getJobs()) {
            google.getManager().process(job);
            job.setOpened(false);
        }

        for (Job job : amazon.getJobs()) {
            amazon.getManager().process(job);
            job.setOpened(false);
        }

        for (User user : application.getUsers()) {
            System.out.println(user.getResume().getInformation().getFullname() + " " + user.getAllNotifications());
        }

        for (Department department : google.getDepartments()) {
            for (Employee employee : department.getEmployees()) {
                System.out.println(employee.getResume().getInformation().getFullname() + " " + employee.getAllNotifications());
            }
        }

        for (Department department : amazon.getDepartments()) {
            for (Employee employee : department.getEmployees()) {
                System.out.println(employee.getResume().getInformation().getFullname() + " " + employee.getAllNotifications());
            }
        }

        System.out.println(application);

    }
}
