package com.alura.djonatah.medvollapi.domain.service;

import com.alura.djonatah.medvollapi.domain.model.doctor.DoctorUpdateData;
import com.alura.djonatah.medvollapi.domain.model.doctor.Doctor;
import com.alura.djonatah.medvollapi.domain.model.common.Address;
import com.alura.djonatah.medvollapi.domain.model.common.AddressData;

public class DoctorUpdate {
    Doctor doctor = null;
    Address address = null;

    public DoctorUpdate(Doctor doctor){
        this.doctor = doctor;
        this.address = doctor.getAddress();
    }

    public void update(DoctorUpdateData doctorUpdateData){
        if(doctorUpdateData.phone() != null)
            doctor.setPhone(doctorUpdateData.phone());
        if(doctorUpdateData.email() != null);
            doctor.setEmail(doctorUpdateData.email());
        if(doctorUpdateData.address() != null)
            updateDoctorAddress(doctorUpdateData.address());
    }
    private void updateDoctorAddress(AddressData addressData){

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

    public void delete(Doctor doctor){
        doctor.setActive(false);
    }
}
