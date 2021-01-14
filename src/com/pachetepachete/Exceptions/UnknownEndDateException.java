package com.pachetepachete.Exceptions;

/*
    Exceptie pentru data finala invalida.
 */

public class UnknownEndDateException extends Exception {
    public UnknownEndDateException(String errorMessage) {
        super(errorMessage);
    }
}
