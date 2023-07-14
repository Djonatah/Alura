package com.alura.djonatah.medvollapi.controller;

import com.alura.djonatah.medvollapi.domain.model.appointment.AppointmentCancelData;
import com.alura.djonatah.medvollapi.domain.model.appointment.AppointmentData;
import com.alura.djonatah.medvollapi.domain.model.appointment.AppointmentDetailData;
import com.alura.djonatah.medvollapi.domain.service.AppointmentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/appointment")
@SecurityRequirement(name = "bearer-key")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @PostMapping
    @Transactional
    public ResponseEntity<AppointmentDetailData> schedule(@RequestBody @Valid AppointmentData appointmentData, UriComponentsBuilder uriBuilder){
        var appointment = appointmentService.schedule(appointmentData);
        var uri = uriBuilder.path("appointment/{id}").buildAndExpand(appointment.getId()).toUri();
        return ResponseEntity.created(uri).body(new AppointmentDetailData(appointment));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<AppointmentDetailData> cancel(@RequestBody @Valid AppointmentCancelData appointmentCancelData){
        appointmentService.cancel(appointmentCancelData);
        return ResponseEntity.noContent().build();
    }
}
