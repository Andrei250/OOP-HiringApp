package com.pachetepachete.Exceptions;

/*
    Exceptie pentru date invalide.
 */

public class InvalidDatesException extends Exception{
    public InvalidDatesException (String errorMessage) {
        super(errorMessage);
    }
}
