package com.alura.djonatah.medvollapi.domain.model.appointment;

public class DataValidationException extends RuntimeException{
    public DataValidationException(String message) {
        super(message);
    }
}
