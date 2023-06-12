package com.alura.djonatah.medvollapi.controller;

import com.alura.djonatah.medvollapi.domain.dataaccess.DoctorRepository;
import com.alura.djonatah.medvollapi.domain.model.doctor.*;
import com.alura.djonatah.medvollapi.domain.service.DoctorUpdate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DoctorRegisterData doctorData, UriComponentsBuilder uriBuilder){
        Doctor doc = new Doctor(doctorData);
        doctorRepository.save(doc);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(doc.getId()).toUri();

        return ResponseEntity.created(uri).body(new DoctorDetailData(doc));
    }

    @GetMapping
    public ResponseEntity<Page<DoctorListData>> list(@PageableDefault(sort = "name") Pageable pageable){
        Page page = doctorRepository.findAllByActiveTrue(pageable).map(DoctorListData::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDetailData> get(@PathVariable Long id) {
        var doctor = doctorRepository.getReferenceById(id);
        return ResponseEntity.ok(new DoctorDetailData(doctor));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DoctorDetailData> update(@RequestBody @Valid DoctorUpdateData doctorData){
        System.out.println(doctorData);
        var doctor = doctorRepository.getReferenceById(doctorData.id());
        var doctorUpdate = new DoctorUpdate(doctor);
        doctorUpdate.update(doctorData);

        return ResponseEntity.ok(new DoctorDetailData(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        var doctor = doctorRepository.getReferenceById(id);
        var doctorUpdate = new DoctorUpdate(doctor);
        doctorUpdate.delete (doctor);
        return ResponseEntity.noContent().build();
    }
}
