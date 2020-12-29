package com.pachetepachete;

import com.pachetepachete.Models.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class AdminPanel extends JFrame implements ListSelectionListener {
    JList<Company> companyJList;
    JList<User> userJList;
    JList<Department> departmentJList;
    JList<Job> jobJList;
    JTextField author;
    JTextField title;
    JTree jt;
    DefaultListModel<User> userDefaultListModel;
    DefaultListModel<Company> companyDefaultListModel;
    DefaultListModel<Department> departmentDefaultListModel;
    DefaultListModel<Job> jobDefaultListModel;

    public AdminPanel() {
        super("Admin Panel");
        setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(500, 300));
        getContentPane().setBackground(Color.blue);
        GridLayout gridLayout = new GridLayout(0, 3);
        gridLayout.setHgap(5);
        gridLayout.setVgap(5);
        setLayout(gridLayout);


        Application application = Application.getInstance();

        companyDefaultListModel = new DefaultListModel<>();
        JScrollPane scrollPaneCompany = new JScrollPane();

        companyDefaultListModel.addAll(application.getCompanies());
        companyJList = new JList<>(companyDefaultListModel);
        scrollPaneCompany.setViewportView(companyJList);
        companyJList.setLayoutOrientation(JList.VERTICAL);
        companyJList.addListSelectionListener(this);
        add(scrollPaneCompany);

        userDefaultListModel = new DefaultListModel<>();
        JScrollPane scrollPaneUser = new JScrollPane();

        userDefaultListModel.addAll(application.getUsers());
        userJList = new JList<>(userDefaultListModel);
        scrollPaneUser.setViewportView(userJList);
        userJList.setLayoutOrientation(JList.VERTICAL);
        add(scrollPaneUser);

        DefaultMutableTreeNode node = new DefaultMutableTreeNode("Company");
        jt = new JTree(node);
        add(jt);

//        JPanel p = new JPanel(new BorderLayout());
//        JPanel p2 = new JPanel(new BorderLayout());
//        JPanel p3 = new JPanel(new BorderLayout());
//        JLabel lb1 = new JLabel("Autor");
//        JLabel lb2 = new JLabel("Titlu");
//
//        p2.add(lb1, BorderLayout.PAGE_START);
//        p2.add(lb2, BorderLayout.PAGE_END);
//
//        author = new JTextField();
//        author.setColumns(50);
//        title = new JTextField();
//        title.setColumns(50);
//        p3.add(author,  BorderLayout.PAGE_START);
//        p3.add(title, BorderLayout.PAGE_END);
//
//        p.add(p2, BorderLayout.LINE_START);
//        p.add(p3, BorderLayout.LINE_END);
//        add(p);
        show();
        pack();
    }

    public JTree getTree(Company company) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(company.getName());
        for (Department department : company.getDepartments()) {
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

            root.add(dep);
        }

        return new JTree(root);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        this.remove(jt);

        if(companyJList.isSelectionEmpty()) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode("Company");
            jt = new JTree(node);
            jt.revalidate();
            this.add(jt);
            this.revalidate();
            return;
        }

        Company company = companyJList.getSelectedValue();
        jt = getTree(company);
        this.add(jt);
        this.revalidate();
    }

}
