package com.pachetepachete.Views;

import com.pachetepachete.Application;
import com.pachetepachete.Models.Education;
import com.pachetepachete.Models.Experience;
import com.pachetepachete.Models.User;
import com.pachetepachete.utils.ObserverFrame;
import com.pachetepachete.utils.SubjectFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//pagina de profil a fiecarui user
public class ProfilePage implements ActionListener{
    private JScrollPane jScrollPane;
    private JPanel bigBox;
    private JPanel antet;
    private JPanel info;
    private JTextField nume;
    private JTextField prenume;
    private JButton search;
    private JTextArea ans;
    private JPanel panel;

    public ProfilePage() {
        panel = new JPanel();
        panel.setLayout(new FlowLayout());

        bigBox = new JPanel(new BorderLayout());
        antet = new JPanel(new FlowLayout());
        info = new JPanel(new FlowLayout());

        antet.setLayout(new FlowLayout());
        nume = new JTextField("Nume", 10);
        prenume = new JTextField("Prenume", 10);
        search = new JButton("CAUTA");
        search.addActionListener(this);

        antet.add(nume);
        antet.add(prenume);
        antet.add(search);
        bigBox.add(antet, BorderLayout.NORTH);

        ans = new JTextArea(20, 50);
        jScrollPane = new JScrollPane(ans);
        ans.setEditable(false);
        info.add(jScrollPane);
        bigBox.add(info, BorderLayout.CENTER);
        panel.add(bigBox);
        bigBox.revalidate();
    }

    //Caut toti userii cu numele si prenumele cautat( sauc are contin substringurile respective)
    @Override
    public void actionPerformed(ActionEvent e) {
        String last_name = nume.getText();
        String first_name = prenume.getText();
        String fName = first_name + " " + last_name;
        ArrayList<User> answer = new ArrayList<>();

        for (User user : Application.getInstance().getUsers()) {
            String fullName = user.getResume().getInformation().getFullname();
            if (fullName.equalsIgnoreCase(fName)|| fullName.contains(fName)) {
                answer.add(user);
            }
        }

        StringBuilder builder = new StringBuilder();

        for (User user : answer) {
            builder.append(user.getResume().getInformation().toString());
            builder.append("\n");
            for (Experience experience : user.getResume().getExperiences()) {
                builder.append(experience.toString());
                builder.append("\n");
            }

            for (Education education : user.getResume().getEducations()) {
                builder.append(education.toString());
                builder.append("\n");
            }

            builder.append("################################\n");
        }
        ans.setText(builder.toString());
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}
