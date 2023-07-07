package com.alura.djonatah.medvollapi.domain.model.appointment;

public class DataValidationException extends Exception{
    public DataValidationException(String message) {
        super(message);
    }
}
