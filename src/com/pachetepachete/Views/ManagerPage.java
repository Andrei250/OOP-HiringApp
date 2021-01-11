package com.pachetepachete.Views;
import com.pachetepachete.Application;
import com.pachetepachete.Models.*;
import com.pachetepachete.utils.ButtonRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Vector;

public class ManagerPage {
    JPanel panel;

    public ManagerPage(Manager manager) {
        panel = new JPanel();

        panel.setLayout(new FlowLayout());
        String[] colNames = {"Nume", "Email", "Scor", "Departament", "Job", "Accept", "Reject"};

        Vector<Object[]> objects = new Vector<>();

        for (Request<Job, Consumer> request : manager.getRequests()) {
            Object[] newRow = {request.getValue1().getResume().getInformation().getFullname(),
                    request.getValue1().getResume().getInformation().getEmail(),
                    request.getScore(), request.getKey().getDepartment(),
                    request.getKey().getName(), "ACCEPT", "REJECT"};
            objects.add(newRow);
        }

        DefaultTableModel model = new DefaultTableModel(objects.toArray(Object[][]::new), colNames);
        JTable table = new JTable(model);

        Action reject = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JTable table = (JTable)e.getSource();
                int row = Integer.parseInt(e.getActionCommand());
                ((DefaultTableModel)table.getModel()).removeRow(row);
            }
        };

        Action accept = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
                int row = Integer.parseInt(e.getActionCommand());
                TableModel model = table.getModel();
                User user = Application.getInstance().findByEmail(model.getValueAt(row, 1).toString());
                Department department = Application.getIfDepartmentExists(model.getValueAt(row, 3).toString(),
                        Application.getInstance().getCompany(manager.getCompanie()).getDepartments());
                Job job = department == null ? null : department.findJobByName(model.getValueAt(row, 4).toString());

                if (Application.getInstance().contains(user) && job != null && job.isOpened()) {
                    department.add(new Employee(user,
                            manager.getCompanie(),
                            job.getSalary()));
                    job.setNoPositions(job.getNoPositions() - 1);

                    Application.getInstance().remove(user);

                    if (job.getNoPositions() == 0) {
                        for (int i = model.getRowCount() - 1; i >= 0; --i) {
                            ((DefaultTableModel)model).removeRow(i);
                        }
                    }
                }

                for (int i = 0; i < model.getRowCount(); ++i) {
                    if (model.getValueAt(i, 1).toString().equalsIgnoreCase(user.getResume().getInformation().getEmail())) {
                        ((DefaultTableModel)model).removeRow(i);
                        i--;
                    }
                }
            }
        };

        new ButtonRenderer(table, accept, 5);
        new ButtonRenderer(table, reject, 6);
        table.setPreferredScrollableViewportSize(new Dimension(700, 400));

        JScrollPane scrollable = new JScrollPane(table);
        panel.add(scrollable);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}
