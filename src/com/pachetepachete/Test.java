package com.pachetepachete;

import com.pachetepachete.Exceptions.InvalidDatesException;
import com.pachetepachete.Models.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws InvalidDatesException {
        Application application = Application.getInstance();
        application.readFromFile("./src/com/pachetepachete/Input/users.txt");
        application.readFromFile("./src/com/pachetepachete/Input/companies.txt");
        application.readFromFile("./src/com/pachetepachete/Input/actions.txt");
    }
}
