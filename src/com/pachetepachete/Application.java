package com.pachetepachete;

import com.pachetepachete.Exceptions.InvalidDatesException;
import com.pachetepachete.Models.*;
import com.pachetepachete.utils.DepartmentFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Application {
    private ArrayList<Company> companies;
    private ArrayList<User> users;
    private static Application application;

    private Application() {
        this.companies = new ArrayList<Company>();
        this.users = new ArrayList<User>();
    }

    public static Application getInstance() {
        if (application == null) {
            synchronized (Application.class) {
                if (application == null) {
                    application = new Application();
                }
            }
        }

        return application;
    }

    public ArrayList<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(ArrayList<Company> companies) {
        this.companies = companies;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void add(Company company) {
        if (!this.companies.contains(company)) {
            this.companies.add(company);
        }
    }

    public void add(User user) {
        if (!this.users.contains(user)) {
            this.users.add(user);
        }
    }

    public boolean remove(Company company) {
        if (this.companies.contains(company)) {
            this.companies.remove(company);
            return true;
        }

        return false;
    }

    public boolean remove(User user) {
        if (this.users.contains(user)) {
            this.users.remove(user);
            return true;
        }

        return false;
    }

    public boolean contains(User user) {
        return this.users.contains(user);
    }

    public User findByEmail(String email) {
        for (User user : this.users) {
            if (user.getResume() != null && user.getResume().getInformation() != null) {
                if (user.getResume().getInformation().getEmail().equals(email)) {
                    return user;
                }
            }
        }

        return null;
    }

    public Company getCompany(String name) {
        for (Company company : this.companies) {
            if (company.getName().compareTo(name) == 0) {
                return company;
            }
        }

        return null;
    }

    public ArrayList<Job> getJobs(List<String> companies) {
        ArrayList<Job> ans = new ArrayList<>();

        for (String st : companies) {
            Company company = getCompany(st);
            if (company != null) {
                ans.addAll(company.getJobs());
            }
        }

        return ans;
    }

    @Override
    public String toString() {
        return "Application:\n " +
                " companies: \n" + companies +
                "\n users: \n" + users +
                '\n';
    }

    public void readFromFile(String path) {
        User user = null;
        Company company = null;
        Manager manager = null;
        Recruiter recruiter = null;
        Consumer.Resume resume = null;

        try {
            File fisier = new File(path);
            Scanner cititorDeFisiere = new Scanner(fisier);
            Scanner wordScanner;
            while (cititorDeFisiere.hasNextLine()) {
                String data = cititorDeFisiere.nextLine().trim();
                wordScanner = new Scanner(data);

                while (wordScanner.hasNext()) {
                    String word = wordScanner.next();

                    if (word.equalsIgnoreCase("user")) {
                        user = new User();
                        application.add(user);
                    } else if (word.equalsIgnoreCase("resume")) {
                        resume = new Consumer.Resume.ResumeBuilder().build();

                        if (user != null && user.getResume() == null) {
                            user.setResume(resume);
                        }

                    } else if (word.equalsIgnoreCase("add")) {
                        word = wordScanner.next();

                        if (word.equalsIgnoreCase("information")) {
                            updateInfo(wordScanner, cititorDeFisiere, user);
                        } else if (word.equalsIgnoreCase("limba")) {
                            String language = wordScanner.next().trim();
                            String lvl = wordScanner.next().trim();

                            if (lvl.equalsIgnoreCase("BEGINNER") || lvl.equalsIgnoreCase("EXPERIENCED") || lvl.equalsIgnoreCase("ADVANCED")) {
                                if (user != null && user.getResume() != null && user.getResume().getInformation() != null) {
                                    user.getResume().getInformation().addNewLanguage(language, lvl);
                                }
                            }
                        } else if (word.equalsIgnoreCase("experience")) {
                            Date start = new SimpleDateFormat("dd/MM/yyyy").parse(cititorDeFisiere.nextLine().trim());
                            String endDate = cititorDeFisiere.nextLine().trim();
                            Date end = endDate.equalsIgnoreCase("null") ? null : new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
                            String position = cititorDeFisiere.nextLine().trim();
                            String place = cititorDeFisiere.nextLine().trim();

                            if (user != null && user.getResume() != null) {
                                user.getResume().add(new Experience(start, end, position, place));
                            }
                        } else if (word.equalsIgnoreCase("education")) {
                            Date start = new SimpleDateFormat("dd/MM/yyyy").parse(cititorDeFisiere.nextLine().trim());
                            String endDate = cititorDeFisiere.nextLine().trim();
                            Date end = endDate.equalsIgnoreCase("null") ? null : new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
                            String faculty = cititorDeFisiere.nextLine().trim();
                            String institution = cititorDeFisiere.nextLine().trim();
                            String gpa = cititorDeFisiere.nextLine().trim();
                            if (gpa.equalsIgnoreCase("NULL")) {
                                gpa = null;
                            }

                            if (user != null && user.getResume() != null) {
                                user.getResume().add(new Education(start, end, institution, faculty, gpa != null ? Double.parseDouble(gpa) : null ));
                            }
                        } else if (word.equalsIgnoreCase("department")) {
                            Department department = new DepartmentFactory().getDepartment(wordScanner.next().trim());

                            if (department != null && company != null) {
                                company.add(department);
                            }
                        } else if (word.equalsIgnoreCase("job")) {
                            String departmentName = wordScanner.next().trim();
                            Department departmentNeeded = null;

                            if (company != null && company.getDepartments() != null) {
                                for (Department department : company.getDepartments()) {
                                    if (departmentName.equalsIgnoreCase("IT") && department instanceof IT) {
                                        departmentNeeded = department;
                                    } else if (departmentName.equalsIgnoreCase("Finance") && department instanceof Finance) {
                                        departmentNeeded = department;
                                    } else if (departmentName.equalsIgnoreCase("Marketing") && department instanceof Marketing) {
                                        departmentNeeded = department;
                                    } else if (departmentName.equalsIgnoreCase("Management") && department instanceof Management) {
                                        departmentNeeded = department;
                                    }
                                }
                            }

                            Job job = getNewJob(cititorDeFisiere, company, departmentName);

                            if (company != null && departmentNeeded != null) {
                                departmentNeeded.add(job);
                            }
                        } else if (word.equalsIgnoreCase("recruiter")) {
                            resume = new Consumer.Resume.ResumeBuilder().build();
                            recruiter = new Recruiter(company, (int) 0.0);
                            recruiter.setResume(resume);
                            updateInfo(wordScanner, cititorDeFisiere, recruiter);
                            int salary = Integer.parseInt(cititorDeFisiere.nextLine().trim());
                            recruiter.setSalariu(salary);
                            if (company != null) {
                                company.add(recruiter);
                            }
                        }
                    } else if (word.equalsIgnoreCase("print")) {
                        System.out.println("#####################################################");
                        System.out.println(application);
                        System.out.println("#####################################################");
                    } else if (word.equalsIgnoreCase("company")) {
                        String cmpName = cititorDeFisiere.nextLine().trim();
                        company = new Company(cmpName, null);
                        resume = new Consumer.Resume.ResumeBuilder().build();
                        manager = new Manager();
                        manager.setResume(resume);
                        updateInfo(wordScanner, cititorDeFisiere, manager);
                        String salary = cititorDeFisiere.nextLine().trim();
                        manager.setSalariu(Integer.parseInt(salary));
                        manager.setCompanie(company);
                        company.setManager(manager);
                        this.add(company);
                    } else if (word.equalsIgnoreCase("apply")) {

                    }

                }
                wordScanner.close();
            }
            cititorDeFisiere.close();
        } catch (FileNotFoundException | ParseException | InvalidDatesException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void updateInfo(Scanner wordScanner, Scanner cititorDeFisiere, Consumer user) throws ParseException {
        String nume = cititorDeFisiere.nextLine().trim();
        String prenume = cititorDeFisiere.nextLine().trim();
        String email = cititorDeFisiere.nextLine().trim();
        String telefon = cititorDeFisiere.nextLine().trim();
        Date data_nastere = new SimpleDateFormat("dd/MM/yyyy").parse(cititorDeFisiere.nextLine().trim());
        String sex = cititorDeFisiere.nextLine().trim();

        if (application.findByEmail(email) == null && data_nastere != null && user != null && user.getResume() != null) {
            user.getResume().setInformation(new Information(nume, prenume, email, telefon, data_nastere, sex));
        }
    }

    private Job getNewJob(Scanner scanner, Company company, String department) {
        String name = scanner.nextLine().trim();
        boolean isOpened = Boolean.parseBoolean(scanner.nextLine().trim());
        int nr = Integer.parseInt(scanner.nextLine().trim());
        int salary = Integer.parseInt(scanner.nextLine().trim());
        ArrayList<Constraint> constraints = new ArrayList<>();

        constraints.add(getContraint(scanner));
        constraints.add(getContraint(scanner));
        constraints.add(getContraint(scanner));

        return new Job(name, company.getName(), isOpened, nr, salary, constraints, department);
    }

    private Constraint getContraint(Scanner scanner) {
        double start, stop;
        String cons = scanner.nextLine().trim();
        Scanner sn = new Scanner(cons);

        start = Double.parseDouble(sn.next().trim());
        stop = Double.parseDouble(sn.next().trim());
        return new Constraint(start, stop);
    }

    public String getDepartmentName(Department department) {
        if (department instanceof IT) {
            return "IT";
        } else if (department instanceof Finance) {
            return "Finance";
        } else if (department instanceof Marketing) {
            return "Marketing";
        } else if (department instanceof Management) {
            return "Management";
        }
        return "";
    }
}
