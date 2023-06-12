package com.alura.djonatah.medvollapi.domain.model.patient;

import com.alura.djonatah.medvollapi.domain.model.common.Address;

public record PatientDetailData(String name, String email, String ssn, boolean active, Address address) {
    public PatientDetailData(Patient patient){
        this(patient.getName(), patient.getEmail(), patient.getSsn(), patient.isActive(), patient.getAddress());
    }
}
