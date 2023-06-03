package com.alura.djonatah.medvollapi.controller;

import com.alura.djonatah.medvollapi.domain.Doctor.DoctorData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("doctors")
public class DoctorController {
    @PostMapping
    public void register(@RequestBody DoctorData doctorData){
        System.out.println(doctorData);
    }
}
