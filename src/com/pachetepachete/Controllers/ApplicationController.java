package com.pachetepachete.Controllers;

import com.pachetepachete.Application;
import com.pachetepachete.Models.Manager;
import com.pachetepachete.Models.Request;
import com.pachetepachete.Views.AdminPanel;
import com.pachetepachete.Views.ManagerPage;
import com.pachetepachete.Views.ProfilePage;
import com.pachetepachete.utils.SubjectFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicationController extends JFrame implements ActionListener {
    private CardLayout layout;
    private JPanel panelsSide;
    private JPanel buttonsSide;

    public ApplicationController() {
        super("Admin Panel");
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1000, 500));
        getContentPane().setBackground(Color.blue);
        setLayout(new BorderLayout());

        Manager manager = Application.getInstance().getCompany("Google").getManager();
        SubjectFrame subject = new SubjectFrame();
        AdminPanel adminPanel = new AdminPanel(subject);
        ManagerPage managerPanel = new ManagerPage(manager, subject);
        ProfilePage profilePanel = new ProfilePage();

        layout = new CardLayout();
        panelsSide = new JPanel(layout);

        panelsSide.add(managerPanel.getPanel(), "1");
        panelsSide.add(adminPanel.getPanel(), "2");
        panelsSide.add(profilePanel.getPanel(), "3");

        buttonsSide = new JPanel();
        JButton adminPage = new JButton("Admin Page");
        JButton managerPage = new JButton("Manager Page");
        JButton profilePage = new JButton("Profile Page");

        adminPage.addActionListener(this);
        managerPage.addActionListener(this);
        profilePage.addActionListener(this);

        buttonsSide.add(managerPage);
        buttonsSide.add(adminPage);
        buttonsSide.add(profilePage);

        getContentPane().add(panelsSide, BorderLayout.NORTH);
        getContentPane().add(buttonsSide, BorderLayout.SOUTH);

        show();
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (((JButton) e.getSource()).getActionCommand().equals("Admin Page")) {
            layout.show(panelsSide, "2");
        } else if (((JButton) e.getSource()).getActionCommand().equals("Manager Page")) {
            layout.show(panelsSide, "1");
        } else if (((JButton) e.getSource()).getActionCommand().equals("Profile Page"))  {
            layout.show(panelsSide, "3");
        }
    }
}
