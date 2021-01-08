package com.pachetepachete.Controllers;

import com.pachetepachete.Application;
import com.pachetepachete.Views.AdminPanel;
import com.pachetepachete.Views.ManagerPage;
import com.pachetepachete.Views.ProfilePage;

import javax.swing.*;
import java.awt.*;

public class ApplicationController extends JFrame {

    public ApplicationController() {
        super("Admin Panel");
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(700, 400));
        getContentPane().setBackground(Color.blue);
        setLayout(new CardLayout());
        AdminPanel adminPanel = new AdminPanel();
        ManagerPage managerPage = new ManagerPage(Application.getInstance().getCompany("Amazon").getManager());
        ProfilePage profilePage = new ProfilePage();

        add(managerPage.getPanel(), "Manager Page");
        add(adminPanel.getPanel(), "Admin Panel");
        add(profilePage.getPanel(), "Profile Page");

        show();
        pack();
    }
}
