package com.alura.djonatah.medvollapi.controller;

import com.alura.djonatah.medvollapi.domain.Patient.PatientData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("patient")
public class PatientController {
    @PostMapping
    public void register(@RequestBody PatientData patientData){
        System.out.println(patientData);
    }
}
