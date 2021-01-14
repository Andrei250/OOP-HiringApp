package com.pachetepachete.Exceptions;

/*
    Exceptie pentru 0 recruiters.
 */

public class NoRecruitersException extends Exception {
    public NoRecruitersException(String errorMessage) {
        super(errorMessage);
        System.out.println(errorMessage);
    }
}
