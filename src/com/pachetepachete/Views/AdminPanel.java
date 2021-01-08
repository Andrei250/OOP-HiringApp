package com.pachetepachete.Views;

import com.pachetepachete.Application;
import com.pachetepachete.Models.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class AdminPanel implements ListSelectionListener, ActionListener {
    JPanel panel;
    JList<Company> companyJList;
    JList<User> userJList;
    JButton button;
    JTree jt;
    DefaultListModel<User> userDefaultListModel;
    DefaultListModel<Company> companyDefaultListModel;
    JPanel p;


    public AdminPanel() {
        panel = new JPanel();

        panel.setLayout(new FlowLayout());
        button = new JButton("Calculeaza salariile");
        button.setEnabled(false);
        button.addActionListener(this);
        panel.add(button);

        GridLayout gridLayout1 = new GridLayout(0, 3);
        gridLayout1.setHgap(5);
        gridLayout1.setVgap(5);
        p = new JPanel(gridLayout1);

        Application application = Application.getInstance();

        companyDefaultListModel = new DefaultListModel<>();
        JScrollPane scrollPaneCompany = new JScrollPane();

        companyDefaultListModel.addAll(application.getCompanies());
        companyJList = new JList<>(companyDefaultListModel);
        scrollPaneCompany.setViewportView(companyJList);
        companyJList.setLayoutOrientation(JList.VERTICAL);
        companyJList.addListSelectionListener(this);
        p.add(scrollPaneCompany);

        userDefaultListModel = new DefaultListModel<>();
        JScrollPane scrollPaneUser = new JScrollPane();

        userDefaultListModel.addAll(application.getUsers());
        userJList = new JList<>(userDefaultListModel);
        scrollPaneUser.setViewportView(userJList);
        userJList.setLayoutOrientation(JList.VERTICAL);
        p.add(scrollPaneUser);

        DefaultMutableTreeNode node = new DefaultMutableTreeNode("Company");
        jt = new JTree(node);
        JScrollPane jScrollPane = new JScrollPane(jt);
        p.add(jt);
        panel.add(p);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JTree getTree(Company company) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(company.getName());
        for (Department department : company.getDepartments()) {
            root.add(getDepNode(department));
        }

        return new JTree(root);
    }

    public JTree getTreeWithBudget(Company company) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(company.getName());
        for (Department department : company.getDepartments()) {
            DefaultMutableTreeNode dep = getDepNode(department);

            dep.add(new DefaultMutableTreeNode("Salarii totale: " + department.getTotalSalaryBudget()));
            root.add(dep);
        }

        return new JTree(root);
    }

    private DefaultMutableTreeNode getDepNode(Department department) {
        DefaultMutableTreeNode dep = new DefaultMutableTreeNode(Application.getInstance().getDepartmentName(department));
        DefaultMutableTreeNode people = new DefaultMutableTreeNode("Empoyees");
        DefaultMutableTreeNode jobs = new DefaultMutableTreeNode("Jobs");

        for (Employee employee : department.getEmployees()) {
            DefaultMutableTreeNode person = new DefaultMutableTreeNode(employee.getResume().getInformation().getFullname());
            people.add(person);
        }

        for (Job job : department.getJobs()) {
            DefaultMutableTreeNode jb = new DefaultMutableTreeNode(job.getName());
            jobs.add(jb);
        }

        dep.add(people);
        dep.add(jobs);

        return dep;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        p.remove(jt);

        if(companyJList.isSelectionEmpty()) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode("Company");
            jt = new JTree(node);
            jt.revalidate();
            p.add(jt);
            p.revalidate();
            button.setEnabled(false);
            return;
        }

        Company company = companyJList.getSelectedValue();
        jt = getTree(company);
        p.add(jt);
        p.revalidate();
        button.setEnabled(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        p.remove(jt);
        Company company = companyJList.getSelectedValue();
        jt = getTreeWithBudget(company);
        p.add(jt);
        p.revalidate();
    }
}
