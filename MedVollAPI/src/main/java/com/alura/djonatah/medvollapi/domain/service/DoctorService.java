package com.alura.djonatah.medvollapi.domain.service;

import com.alura.djonatah.medvollapi.domain.repositories.DoctorRepository;
import com.alura.djonatah.medvollapi.domain.model.doctor.*;
import com.alura.djonatah.medvollapi.domain.model.common.Address;
import com.alura.djonatah.medvollapi.domain.model.common.AddressData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor create(DoctorRegisterData doctorRegisterData){
        var doctor = new Doctor(doctorRegisterData);
        return doctorRepository.save(doctor);
    }

    public Doctor update(DoctorUpdateData doctorUpdateData){

        var doctor = doctorRepository.getReferenceById(doctorUpdateData.id());

        if(doctorUpdateData.phone() != null)
            doctor.setPhone(doctorUpdateData.phone());
        if(doctorUpdateData.email() != null);
            doctor.setEmail(doctorUpdateData.email());
        if(doctorUpdateData.address() != null)
            updateDoctorAddress(doctor.getAddress(), doctorUpdateData.address());

        return doctor;
    }

    private void updateDoctorAddress(Address address, AddressData addressData){

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

    public void delete(Long id){
        var doctor = doctorRepository.getReferenceById(id);
        doctor.setActive(false);
    }

    public Page list(Pageable pageable) {
        return doctorRepository.findAllByActiveTrue(pageable).map(DoctorListData::new);
    }

    public DoctorDetailData get(Long id) {
        var doctor = doctorRepository.getReferenceById(id);
        return new DoctorDetailData(doctor);
    }
}
