package com.alura.djonatah.medvollapi.controller;

import com.alura.djonatah.medvollapi.domain.model.appointment.AppointmentData;
import com.alura.djonatah.medvollapi.domain.model.appointment.AppointmentDetailData;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @PostMapping
    @Transactional
    public ResponseEntity<AppointmentDetailData> schedule(@RequestBody @Valid AppointmentData appointmentData){
        return ResponseEntity.ok(new AppointmentDetailData(null, null, null, null));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<AppointmentDetailData> cancel(@RequestBody @Valid AppointmentData appointmentData){
        return ResponseEntity.noContent().build();
    }
}
