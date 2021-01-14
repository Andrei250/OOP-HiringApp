package com.pachetepachete.Controllers;

import com.pachetepachete.Application;
import com.pachetepachete.Exceptions.InvalidDatesException;
import com.pachetepachete.Models.*;
import com.pachetepachete.utils.DepartmentFactory;
import com.pachetepachete.utils.Pair;
import com.pachetepachete.utils.PairArray;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/*
    Controller de citire din fisierele Json.
    Fiecare metoda citeste dintr-un fisier specific (Conform numelui metodei).
    Am folosit singleton, deoarece am vrut sa folosesc o singura instanta.
    Mai multe instante nu erau necesare.
 */
public class ReadController {
    private static ReadController readController;
    private Application application;
    private JSONParser parser;

    private ReadController() {
        application = Application.getInstance();
        parser = new JSONParser();
    }

    public static ReadController getInstance() {
        if (readController == null) {
            synchronized (ReadController.class) {
                if (readController == null) {
                    readController = new ReadController();
                }
            }
        }

        return readController;
    }

    /*
        Citirea companiilor.
     */

    public void readJSONCompanies(String path) {
        DepartmentFactory factory = new DepartmentFactory();

        try {
            Object obj = parser.parse(new FileReader(path));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray companies = (JSONArray) jsonObject.get("companies");

            for (JSONObject company : (Iterable<JSONObject>) companies) {
                String name = (String) company.get("name");
                ArrayList<Department> departments = new ArrayList<>();

                for (String department : (Iterable<String>) ((JSONArray) company.get("departments"))) {
                    Department toAdd = factory.getDepartment(department);

                    if (toAdd != null) {

                        if (Application.getIfDepartmentExists(department, departments) == null) {
                            departments.add(toAdd);
                        }
                    }
                }

                Company newComp = new Company(name, null);

                newComp.setDepartments(departments);
                application.add(newComp);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
        Citirea joburilor.
     */

    public void readJSONJobs(String path) {
        try {
            Object obj = parser.parse(new FileReader(path));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray jobs = (JSONArray) jsonObject.get("jobs");

            for (JSONObject jobObj : (Iterable<JSONObject>) jobs) {
                Company cmp = application.getCompany((String) jobObj.get("company"));
                String name = (String) jobObj.get("name");
                int salary = Integer.parseInt(jobObj.get("salary").toString());
                int noPositions = Integer.parseInt(jobObj.get("noPositions").toString());
                String department = (String) jobObj.get("department");
                ArrayList<Constraint> constraints = new ArrayList<>();

                constraints.add(new Constraint(((JSONObject)((JSONArray) jobObj.get("constraints")).get(0)).get("start") != null ?
                                            Double.parseDouble(((JSONObject)((JSONArray) jobObj.get("constraints")).get(0)).get("start").toString()) : null,
                                            ((JSONObject)((JSONArray) jobObj.get("constraints")).get(0)).get("end") != null ?
                                            Double.parseDouble(((JSONObject)((JSONArray) jobObj.get("constraints")).get(0)).get("end").toString()) : null));
                constraints.add(new Constraint(((JSONObject)((JSONArray) jobObj.get("constraints")).get(1)).get("start") != null ?
                                            Double.parseDouble(((JSONObject)((JSONArray) jobObj.get("constraints")).get(1)).get("start").toString()) : null,
                                            ((JSONObject)((JSONArray) jobObj.get("constraints")).get(1)).get("end") != null ?
                                            Double.parseDouble(((JSONObject)((JSONArray) jobObj.get("constraints")).get(1)).get("end").toString()) : null));
                constraints.add(new Constraint(((JSONObject)((JSONArray) jobObj.get("constraints")).get(2)).get("start") != null ?
                                            Double.parseDouble(((JSONObject)((JSONArray) jobObj.get("constraints")).get(2)).get("start").toString()) : null,
                                            ((JSONObject)((JSONArray) jobObj.get("constraints")).get(2)).get("end") != null ?
                                            Double.parseDouble(((JSONObject)((JSONArray) jobObj.get("constraints")).get(2)).get("end").toString()): null));


                Job job = new Job(name, cmp.getName(), true, noPositions, salary, constraints, department);
                Department department1 = cmp.getDepartmentByName(department);

                if (department1 != null) {
                    department1.add(job);
                    Application.getInstance().getCompany(job.getCompany()).notifyAllObserverOfCanceling("S-a adaugat jobul " + job.getName() + " din compania " + job.getCompany());
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
        Citirea consumerilor.
     */
    public void readJSONConsumers(String path) {
        try {
            Object obj = parser.parse(new FileReader(path));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray employees = (JSONArray) jsonObject.get("employees");

            //EMPLOYEES

            for (JSONObject empObj : (Iterable<JSONObject>) employees) {
                String currentComp = null;
                String currentDep = null;
                ArrayList<Experience> experiences = new ArrayList<>();
                int salary = Integer.parseInt(empObj.get("salary").toString());

                JSONArray exps = (JSONArray) empObj.get("experience");

                for (JSONObject exp : (Iterable<JSONObject>) exps) {
                    String company = (String) exp.get("company");
                    String position = (String) exp.get("position");
                    String department = (String) exp.get("departament");
                    Date start = exp.get("start_date") != null ? new SimpleDateFormat("dd.MM.yyyy").parse((String) exp.get("start_date")) : null;
                    Date end = exp.get("end_date") != null ? new SimpleDateFormat("dd.MM.yyyy").parse((String) exp.get("start_date")) : null;

                    if (end == null) {
                        currentComp = company;
                        currentDep = department;
                    }

                    experiences.add(new Experience(start, end, position, company, department));
                }

                Collections.sort(experiences);

                Company cmp = application.getCompany(currentComp);
                Employee emp = new Employee(getEmployee(empObj), currentComp, salary);
                Department dept = cmp.getDepartmentByName(currentDep);

                emp.getResume().setExperiences(experiences);
                cmp.add(emp, dept);

            }

            //RECRUITERS
            JSONArray recruiters = (JSONArray) jsonObject.get("recruiters");

            for (JSONObject empObj : (Iterable<JSONObject>) recruiters) {
                String currentComp = null;
                ArrayList<Experience> experiences = new ArrayList<>();
                int salary = Integer.parseInt(empObj.get("salary").toString());
                JSONArray exps = (JSONArray) empObj.get("experience");

                for (JSONObject exp : (Iterable<JSONObject>) exps) {
                    String company = (String) exp.get("company");
                    String position = (String) exp.get("position");
                    String department = (String) exp.get("departament");
                    Date start = exp.get("start_date") != null ? new SimpleDateFormat("dd.MM.yyyy").parse((String) exp.get("start_date")) : null;
                    Date end = exp.get("end_date") != null ? new SimpleDateFormat("dd.MM.yyyy").parse((String) exp.get("end_date")) : null;

                    if (end == null) {
                        currentComp = company;
                    }

                    experiences.add(new Experience(start, end, position, company, department));
                }

                Collections.sort(experiences);

                Recruiter recruiter = new Recruiter(getEmployee(empObj), currentComp, salary);
                recruiter.getResume().setExperiences(experiences);
                Company cmp = application.getCompany(recruiter.getCompanie());

                cmp.add(recruiter);

            }

            //USERS
            JSONArray users = (JSONArray) jsonObject.get("users");

            for (JSONObject empObj : (Iterable<JSONObject>) users) {
                ArrayList<Experience> experiences = new ArrayList<>();
                JSONArray exps = (JSONArray) empObj.get("experience");

                for (JSONObject exp : (Iterable<JSONObject>) exps) {
                    String company = (String) exp.get("company");
                    String position = (String) exp.get("position");
                    Date start = exp.get("start_date") != null ? new SimpleDateFormat("dd.MM.yyyy").parse((String) exp.get("start_date")) : null;
                    Date end = exp.get("end_date") != null ? new SimpleDateFormat("dd.MM.yyyy").parse((String) exp.get("end_date")) : null;

                    experiences.add(new Experience(start, end, position, company));
                }

                Collections.sort(experiences);

                User user = getEmployee(empObj);
                user.getResume().setExperiences(experiences);

                ArrayList<String> following = new ArrayList<>();
                JSONArray comps = (JSONArray) empObj.get("interested_companies");

                for (String comp : (Iterable<String>) comps) {
                    following.add(comp);
                    Application.getInstance().getCompany(comp).attach(user);
                }

                user.setFollowing(following);
                application.add(user);
            }

            //MANAGERS

            JSONArray managers = (JSONArray) jsonObject.get("managers");

            for (JSONObject empObj : (Iterable<JSONObject>) managers) {
                String currentComp = null;
                ArrayList<Experience> experiences = new ArrayList<>();
                int salary = Integer.parseInt(empObj.get("salary").toString());
                JSONArray exps = (JSONArray) empObj.get("experience");

                for (JSONObject exp : (Iterable<JSONObject>) exps) {
                    String company = (String) exp.get("company");
                    String position = (String) exp.get("position");
                    String department = (String) exp.get("departament");
                    Date start = exp.get("start_date") != null ? new SimpleDateFormat("dd.MM.yyyy").parse((String) exp.get("start_date")) : null;
                    Date end = exp.get("end_date") != null ? new SimpleDateFormat("dd.MM.yyyy").parse((String) exp.get("end_date")) : null;

                    if (end == null) {
                        currentComp = company;
                    }

                    experiences.add(new Experience(start, end, position, company, department));
                }

                Collections.sort(experiences);

                Manager manager = new Manager(getEmployee(empObj), currentComp, salary);
                manager.getResume().setExperiences(experiences);
                Company cmp = application.getCompany(manager.getCompanie());

                cmp.setManager(manager);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    Citirea unui simlu User pe care il convertesc apoi in entitatea buna.
     */
    private User getEmployee(JSONObject empObj) throws InvalidDatesException, ParseException {
        String name = (String) empObj.get("name");
        String email = (String) empObj.get("email");
        String phone = (String) empObj.get("phone");
        Date date_of_birth = new SimpleDateFormat("dd.MM.yyyy").parse((String) empObj.get("date_of_birth"));
        String gender = (String) empObj.get("genre");
        ArrayList<Education> educations = new ArrayList<>();
        PairArray<String, String> languages = new PairArray<String, String>();
        JSONArray lang = (JSONArray) empObj.get("languages");
        JSONArray lvls = (JSONArray) empObj.get("languages_level");

        for (int i = 0; i < lang.size(); ++i) {
            languages.add(new Pair<>((String) lang.get(i), (String) lvls.get(i)));
        }

        JSONArray edus = (JSONArray) empObj.get("education");

        for (JSONObject edu : (Iterable<JSONObject>) edus) {
            String lvl = (String) edu.get("level");
            String institution = (String) edu.get("name");
            Double grade = Double.parseDouble(edu.get("grade").toString());
            Date start = (String) edu.get("start_date") != null ? new SimpleDateFormat("dd.MM.yyyy").parse((String) edu.get("start_date")) : null;
            Date end = (String) edu.get("end_data") != null ? new SimpleDateFormat("dd.MM.yyyy").parse((String) edu.get("end_data")) : null;

            educations.add(new Education(start, end, institution, lvl, grade));
        }

        Collections.sort(educations);

        User emp = new User();
        String[] splited = name.split("\\s+");
        StringBuilder prenume = new StringBuilder();
        String nume = splited[splited.length - 1];

        for (int i = 0; i < splited.length - 1; ++i) {
            prenume.append(splited[i]).append(" ");
        }

        Information info = new Information(nume, prenume.toString(), email, phone, date_of_birth, gender);

        emp.setResume(new Consumer.Resume.ResumeBuilder().information(info).education(educations).build());

        return emp;
    }

    public void readJSONSocial(String path) {
        try {
            Object obj = parser.parse(new FileReader(path));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray social = (JSONArray) jsonObject.get("connections");

            for (JSONObject connection : (Iterable<JSONObject>) social) {
                Consumer cons1 = application.getConsumerByEmail((String) connection.get("from"));
                Consumer cons2 = application.getConsumerByEmail((String) connection.get("to"));

                cons1.add(cons2);
                cons2.add(cons1);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
