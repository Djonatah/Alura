package com.alura.djonatah.medvollapi.controller;

import com.alura.djonatah.medvollapi.domain.model.data.DoctorRepository;
import com.alura.djonatah.medvollapi.domain.model.patient.PatientUpdateData;
import com.alura.djonatah.medvollapi.domain.model.patient.Patient;
import com.alura.djonatah.medvollapi.domain.model.patient.PatientData;
import com.alura.djonatah.medvollapi.domain.model.patient.PatientListData;
import com.alura.djonatah.medvollapi.domain.model.data.PatientRepository;
import com.alura.djonatah.medvollapi.domain.service.PatientUpdate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public Page<PatientListData> list(Pageable pageable){
        return patientRepository.findAllByActiveTrue(pageable).map(PatientListData::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid PatientUpdateData patientUpdateData){
        var patient = patientRepository.getReferenceById(patientUpdateData.id());
        PatientUpdate patientUpdate = new PatientUpdate(patient);
        patientUpdate.update(patientUpdateData);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void update(@PathVariable @Valid Long id){
        var patient = patientRepository.getReferenceById(id);
        PatientUpdate patientUpdate = new PatientUpdate(patient);
        patientUpdate.delete(patient);
    }
}
