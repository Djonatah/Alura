package com.alura.djonatah.medvollapi.controller;

import com.alura.djonatah.medvollapi.domain.model.patient.*;
import com.alura.djonatah.medvollapi.domain.dataaccess.PatientRepository;
import com.alura.djonatah.medvollapi.domain.service.PatientUpdate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("patient")
public class PatientController {

    @Autowired
    PatientRepository patientRepository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid PatientData patientData, UriComponentsBuilder uriBuilder){
        Patient patient = new Patient(patientData);
        patientRepository.save(patient);

        var uri = uriBuilder.path("patient/{id}").buildAndExpand(patient.getId()).toUri();

        return ResponseEntity.created(uri).body(new PatientDetailData(patient));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable Long id){
        var patient = patientRepository.getReferenceById(id);
        return ResponseEntity.ok(new PatientDetailData(patient));
    }

    @GetMapping
    public ResponseEntity<Page<PatientListData>> list(Pageable pageable){
        return ResponseEntity.ok(patientRepository.findAllByActiveTrue(pageable).map(PatientListData::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid PatientUpdateData patientUpdateData){
        var patient = patientRepository.getReferenceById(patientUpdateData.id());
        PatientUpdate patientUpdate = new PatientUpdate(patient);
        patientUpdate.update(patientUpdateData);
        return ResponseEntity.ok(new PatientDetailData(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity update(@PathVariable @Valid Long id){
        var patient = patientRepository.getReferenceById(id);
        PatientUpdate patientUpdate = new PatientUpdate(patient);
        patientUpdate.delete(patient);
        return ResponseEntity.noContent().build();
    }
}
