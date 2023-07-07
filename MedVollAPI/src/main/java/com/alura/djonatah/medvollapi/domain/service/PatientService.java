package com.alura.djonatah.medvollapi.domain.service;

import com.alura.djonatah.medvollapi.domain.model.common.Address;
import com.alura.djonatah.medvollapi.domain.model.common.AddressData;
import com.alura.djonatah.medvollapi.domain.model.patient.Patient;
import com.alura.djonatah.medvollapi.domain.model.patient.PatientUpdateData;

public class PatientService {
    Patient patient;
    Address address;

    public PatientService(Patient patient){
        this.patient = patient;
        this.address = patient.getAddress();
    }

    public void update(PatientUpdateData patientUpdateData){
        if(patientUpdateData.name() != null)
            this.patient.setName(patientUpdateData.name());
        if(patientUpdateData.phone() != null)
            this.patient.setPhone(patientUpdateData.phone());
        if(patientUpdateData.address() != null)
            updatePatientAddress(patientUpdateData.address());
    }

    private void updatePatientAddress(AddressData addressData){
        if(addressData.street() != null)
            address.setStreet(addressData.street());
        if(addressData.zone() != null)
            address.setZone(addressData.zone());
        if(addressData.city() != null)
            address.setCity(addressData.city());
        if(addressData.state() != null)
            address.setState(addressData.state());
        if(addressData.zip() != null)
            address.setZip(addressData.zip());
        if(addressData.number() != null)
            address.setNumber(addressData.number());
        if(addressData.additionalInfo() != null)
            address.setAdditionalInfo(addressData.additionalInfo());
    }

    public void delete(Patient patient){
        patient.setActive(false);
    }
}
