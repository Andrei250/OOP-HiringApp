package com.pachetepachete.Views;

import com.pachetepachete.Models.Consumer;
import com.pachetepachete.Models.Job;
import com.pachetepachete.Models.Request;

import javax.swing.*;
import java.awt.*;

public class RequestsRenderer extends JPanel implements ListCellRenderer<Request<Job, Consumer>> {
    private JButton accept, reject;
    private JTextArea info;

    public RequestsRenderer() {
        setOpaque(true);
        accept = new JButton("Accept");
        reject = new JButton("Reject");
        info = new JTextArea();
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Request<Job, Consumer>> list, Request<Job, Consumer> value, int index, boolean isSelected, boolean cellHasFocus) {
        this.setLayout(new BorderLayout());

        accept.setBackground(Color.green);
        accept.setForeground(Color.WHITE);
//        accept.addActionListener(new AcceptRequestController((JList<Request<Job, Consumer>>) list, index));
        reject.setBackground(Color.red);
        reject.setForeground(Color.WHITE);

        this.add(reject, BorderLayout.WEST);
        this.add(accept, BorderLayout.EAST);

        String name = value.getValue1().getResume().getInformation().getFullname();
        Double score = value.getScore();

        info.setText("\nNume: " + name + "\nScor: " + score.toString() + "\nDepartament: " + value.getKey().getDepartment() + "\n");
        this.add(info, BorderLayout.CENTER);
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        return this;
    }
}
