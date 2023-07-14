package com.alura.djonatah.medvollapi.domain.repositories;

import com.alura.djonatah.medvollapi.domain.model.appointment.Appointment;
import com.alura.djonatah.medvollapi.domain.model.common.AddressData;
import com.alura.djonatah.medvollapi.domain.model.doctor.Doctor;
import com.alura.djonatah.medvollapi.domain.model.doctor.DoctorRegisterData;
import com.alura.djonatah.medvollapi.domain.model.doctor.Speciality;
import com.alura.djonatah.medvollapi.domain.model.patient.Patient;
import com.alura.djonatah.medvollapi.domain.model.patient.PatientData;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    EntityManager em;

    @Test
    @DisplayName("Should return null when there is no doctor with such speciality")
    void selectCalendarFreeDoctorForAppointmentCase1() {
        LocalDateTime nextMonday10am = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);

        var selectedDoctor = doctorRepository.selectCalendarFreeDoctorForAppointment(Speciality.DERMATOLOGY,nextMonday10am);

        assertThat(selectedDoctor).isNull();
    }

    @Test
    @DisplayName("Should return null when there is no doctor available at given time")
    void selectCalendarFreeDoctorForAppointmentCase2() {
        LocalDateTime nextMonday10am = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);
        var doctor = saveDoctor("Jean Paul", Speciality.CARDIOLOGY, true);
        var patient = savePatient("Luiz Henrique", true);

        saveAppointment(doctor, patient, nextMonday10am);

        var selectedDoctor = doctorRepository.selectCalendarFreeDoctorForAppointment(Speciality.DERMATOLOGY,nextMonday10am);
        assertThat(selectedDoctor).isNull();
    }

    @Test
    @DisplayName("Should return a proper doctor when doctor is available at given time")
    void selectCalendarFreeDoctorForAppointmentCase3() {
        LocalDateTime nextMonday10am = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);
        var doctor = saveDoctor("Jean Paul", Speciality.CARDIOLOGY, true);

        var selectedDoctor = doctorRepository.selectCalendarFreeDoctorForAppointment(Speciality.CARDIOLOGY,nextMonday10am);
        assertThat(doctor.equals(selectedDoctor)).isTrue();
    }

    @Test
    @DisplayName("Should return a random doctors upon multiple schedules")
    void selectCalendarFreeDoctorForAppointmentCase4() {
        LocalDateTime nextMonday10am = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);
        LocalDateTime nextMonday11am = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(11,0);
        saveDoctor("Jean Paul", Speciality.CARDIOLOGY, true);
        saveDoctor("Drake House", Speciality.CARDIOLOGY, true);
        saveDoctor("Mirna Lis", Speciality.CARDIOLOGY, true);
        saveDoctor("Isaque Drummond", Speciality.CARDIOLOGY, true);

        var doctor1 = doctorRepository.selectCalendarFreeDoctorForAppointment(Speciality.CARDIOLOGY,nextMonday10am);
        var doctor2 = doctorRepository.selectCalendarFreeDoctorForAppointment(Speciality.CARDIOLOGY,nextMonday11am);
        assertThat(doctor1.equals(doctor2)).isFalse();
    }

    private void saveAppointment(Doctor doctor, Patient patient, LocalDateTime date) {
        em.persist(new Appointment(null, doctor, patient, date, null));
    }

    private Doctor saveDoctor(String name, Speciality speciality, boolean active) {
        var doctorData = getDoctorData(name, speciality, active);
        var doctor = new Doctor(doctorData);
        em.persist(doctor);
        return doctor;
    }

    private Patient savePatient(String name, boolean active) {
        var patientData = getPatientData(name, active);
        var patient = new Patient(patientData);
        em.persist(patient);
        return patient;
    }

    private PatientData getPatientData(String name, boolean active) {
        return new PatientData(name,
                "test@medvollapi.com",
                "(888)777-7777",
                "555-888-888",
                getAddressData());
    }

    private DoctorRegisterData getDoctorData(String name, Speciality speciality, boolean active){
        return new DoctorRegisterData(name,
                "012345678",
                "(888)777-7777",
                "test@medvollapi.com",
                speciality,
                getAddressData());
    }

    private AddressData getAddressData() {
        return new AddressData("street test",
                "zone test",
                "88888",
                "city test",
                "state test",
                "house test",
                "12345");
    }


}