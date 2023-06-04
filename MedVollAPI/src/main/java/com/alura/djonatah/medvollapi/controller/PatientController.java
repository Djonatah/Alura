package com.alura.djonatah.medvollapi.controller;

import com.alura.djonatah.medvollapi.domain.Patient.Patient;
import com.alura.djonatah.medvollapi.domain.Patient.PatientData;
import com.alura.djonatah.medvollapi.domain.Patient.PatientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("patient")
public class PatientController {

    @Autowired
    PatientRepository patientRepository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid PatientData patientData){
        Patient patient = new Patient(patientData);
        patientRepository.save(patient);
    }
}
