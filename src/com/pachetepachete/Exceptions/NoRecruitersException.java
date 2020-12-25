package com.pachetepachete.Exceptions;

public class NoRecruitersException extends Exception {
    public NoRecruitersException(String errorMessage) {
        super(errorMessage);
        System.out.println(errorMessage);
    }
}
