package com.pachetepachete;

import com.pachetepachete.Controllers.ReadController;
import com.pachetepachete.Exceptions.InvalidDatesException;
import com.pachetepachete.Exceptions.NoRecruitersException;
import com.pachetepachete.Models.Company;
import com.pachetepachete.Models.Job;
import com.pachetepachete.Models.User;

public class Test {
    public static void main(String[] args) throws InvalidDatesException, NoRecruitersException {
        Application application = Application.getInstance();
        ReadController readController = ReadController.getInstance();

        readController.readJSONCompanies("./src/com/pachetepachete/Input/companies.json");
        readController.readJSONConsumers("./src/com/pachetepachete/Input/consumers.json");
        readController.readJSONJobs("./src/com/pachetepachete/Input/jobs.json");
        readController.readJSONSocial("./src/com/pachetepachete/Input/connections.json");

        for (User user : application.getUsers()) {
            System.out.println(user.getAllNotifications());
            for (String cmp : user.getFollowing()) {
                Company company = application.getCompany(cmp);

                for (Job job : company.getJobs()) {
                    job.apply(user);
                }
            }
        }

    }
}
