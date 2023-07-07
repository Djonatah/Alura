package com.alura.djonatah.medvollapi.controller;

import com.alura.djonatah.medvollapi.domain.model.doctor.*;
import com.alura.djonatah.medvollapi.domain.service.DoctorService;
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
    DoctorService doctorService = null;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DoctorRegisterData doctorData, UriComponentsBuilder uriBuilder){
        Doctor doc = doctorService.create(doctorData);
        var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(doc.getId()).toUri();
        return ResponseEntity.created(uri).body(new DoctorDetailData(doc));
    }

    @GetMapping
    public ResponseEntity<Page<DoctorListData>> list(@PageableDefault(sort = "name") Pageable pageable){
        Page page = doctorService.list(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDetailData> get(@PathVariable Long id) {
        var doctorData = doctorService.get(id);
        return ResponseEntity.ok(doctorData);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DoctorDetailData> update(@RequestBody @Valid DoctorUpdateData doctorData){
        var doctor = doctorService.update(doctorData);
        return ResponseEntity.ok(new DoctorDetailData(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        doctorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
