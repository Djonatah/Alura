package com.alura.djonatah.medvollapi.controller;

import com.alura.djonatah.medvollapi.domain.Doctor.Doctor;
import com.alura.djonatah.medvollapi.domain.Doctor.DoctorData;
import com.alura.djonatah.medvollapi.domain.Doctor.DoctorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid DoctorData doctorData){
        Doctor doc = new Doctor(doctorData);
        doctorRepository.save(doc);
        System.out.println(doctorData);
    }
}
