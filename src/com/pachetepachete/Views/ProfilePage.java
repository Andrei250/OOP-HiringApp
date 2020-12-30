package com.pachetepachete.Views;

import com.pachetepachete.Application;
import com.pachetepachete.Models.Education;
import com.pachetepachete.Models.Experience;
import com.pachetepachete.Models.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProfilePage extends JFrame implements ActionListener {
    JScrollPane jScrollPane;
    JPanel bigBox;
    JPanel antet;
    JPanel info;
    JTextField nume;
    JTextField prenume;
    JButton search;
    JTextArea ans;

    public ProfilePage() {
        super("Profile Page");
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(700, 400));
        getContentPane().setBackground(Color.blue);
        setLayout(new FlowLayout());
        show();
        pack();

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
        add(bigBox);
        bigBox.revalidate();

    }

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
}
