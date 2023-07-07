package com.alura.djonatah.medvollapi.domain.service;

import com.alura.djonatah.medvollapi.domain.repositories.AppointmentRepository;
import com.alura.djonatah.medvollapi.domain.repositories.DoctorRepository;
import com.alura.djonatah.medvollapi.domain.repositories.PatientRepository;
import com.alura.djonatah.medvollapi.domain.model.appointment.Appointment;
import com.alura.djonatah.medvollapi.domain.model.appointment.AppointmentCancelData;
import com.alura.djonatah.medvollapi.domain.model.appointment.AppointmentData;
import com.alura.djonatah.medvollapi.domain.model.appointment.DataValidationException;
import com.alura.djonatah.medvollapi.domain.model.doctor.Doctor;
import com.alura.djonatah.medvollapi.domain.model.patient.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    public void cancel(AppointmentCancelData cancelData) throws DataValidationException {
        if(! appointmentRepository.existsById(cancelData.appointmentId()))
            throw new DataValidationException("Unable to find appointment in db");

        var appointment = appointmentRepository.getReferenceById(cancelData.appointmentId());
        appointment.setCancelReason(cancelData.reason());
    }

    public Appointment schedule(AppointmentData appointmentData) throws DataValidationException {
        var patient = getPatientFromDB(appointmentData.patientId());
        var doctor = selectDoctorForAppointment(appointmentData);
        var appointment = new Appointment(null,doctor, patient, appointmentData.date(), null);
        appointmentRepository.save(appointment);
        return appointment;
    }

    private Patient getPatientFromDB(Long patientId) throws DataValidationException {

        if(! patientRepository.existsById(patientId)) {
            throw new DataValidationException("Unable to find patient in DB: " +patientId);
        }

        var patient = patientRepository.findById(patientId).get();
        if(! patient.isActive())
            throw new DataValidationException("Patient is set as disabled: " +patientId);

        return patient;
    }

    private Doctor selectDoctorForAppointment(AppointmentData appointmentData) throws DataValidationException {
        if(appointmentData.doctorId() != null)
            return selectExistingDoctor(appointmentData);
        else
            return pickCalendarFreeDoctorForAppointment(appointmentData);
    }

    private Doctor pickCalendarFreeDoctorForAppointment(AppointmentData appointmentData) throws DataValidationException {
        var doctor = doctorRepository.selectCalendarFreeDoctorForAppointment(appointmentData.speciality(), appointmentData.date());
        if(doctor == null){
            throw new DataValidationException("Unable to select doctor for appointment: " + appointmentData.patientId());
        }
        return doctor;
    }

    private Doctor selectExistingDoctor(AppointmentData appointmentData) throws DataValidationException {
        if(! doctorRepository.existsById(appointmentData.doctorId()))
            throw new DataValidationException("Invalid doctor for appointment: " + appointmentData.doctorId());
        return doctorRepository.getReferenceById(appointmentData.doctorId());
    }
}
