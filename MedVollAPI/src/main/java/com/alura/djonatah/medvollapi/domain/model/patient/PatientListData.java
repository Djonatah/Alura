package com.alura.djonatah.medvollapi.domain.model.patient;

public record PatientListData(String name, String email, String ssn, boolean active) {
    public PatientListData(Patient patient){
        this(patient.getName(), patient.getEmail(), patient.getSsn(), patient.isActive());
    }
}
