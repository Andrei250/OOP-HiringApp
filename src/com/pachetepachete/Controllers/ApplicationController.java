package com.pachetepachete.Controllers;

import javax.swing.*;
import java.awt.*;

public class ApplicationController extends JFrame {

    public ApplicationController() {
        super("Admin Panel");
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(700, 400));
        getContentPane().setBackground(Color.blue);
        setLayout(new CardLayout());

        show();
        pack();
    }
}
