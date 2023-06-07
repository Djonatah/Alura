package com.alura.djonatah.medvollapi.controller;

import com.alura.djonatah.medvollapi.domain.model.data.DoctorRepository;
import com.alura.djonatah.medvollapi.domain.model.doctor.*;
import com.alura.djonatah.medvollapi.domain.service.DoctorUpdate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid DoctorRegisterData doctorData){
        Doctor doc = new Doctor(doctorData);
        doctorRepository.save(doc);
        System.out.println(doctorData);
    }

    @GetMapping
    public Page<DoctorListData> list(@PageableDefault(sort = "name") Pageable pageable){
        return doctorRepository.findAllByActiveTrue(pageable).map(DoctorListData::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid DoctorUpdateData doctorData){
        System.out.println(doctorData);
        //List<Doctor> list = doctorRepository.findAll();
        var doctor = doctorRepository.getReferenceById(doctorData.id());
        var doctorUpdate = new DoctorUpdate(doctor);
        doctorUpdate.update(doctorData);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id){
        var doctor = doctorRepository.getReferenceById(id);
        var doctorUpdate = new DoctorUpdate(doctor);
        doctorUpdate.delete (doctor);
    }
}
