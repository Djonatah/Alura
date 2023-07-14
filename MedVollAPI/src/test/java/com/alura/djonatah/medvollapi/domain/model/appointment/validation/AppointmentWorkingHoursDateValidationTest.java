package com.alura.djonatah.medvollapi.domain.model.appointment.validation;

import com.alura.djonatah.medvollapi.domain.model.appointment.AppointmentData;
import com.alura.djonatah.medvollapi.domain.model.appointment.DataValidationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;


@SpringBootTest
class AppointmentWorkingHoursDateValidationTest {

    @Autowired
    AppointmentWorkingHoursDateValidation workingHoursDateValidation;

    @Test
    @DisplayName("should not validate appoints at or before 6AM")
    public void validateWorkingHoursDateValidation6AM(){
        var appointmentDate6AM = LocalDateTime.now().withHour(6);
        var appointment6AM = new AppointmentData(null, null, appointmentDate6AM, null);

        Assertions.assertThatExceptionOfType(DataValidationException.class)
                .isThrownBy(() -> workingHoursDateValidation.validate(appointment6AM))
                .withMessageContaining("Schedule date should be Monday through Friday / 7AM-7PM");

        var appointmentDate4AM = LocalDateTime.now().withHour(4);
        var appointment4AM = new AppointmentData(null, null, appointmentDate4AM, null);

        Assertions.assertThatExceptionOfType(DataValidationException.class)
                .isThrownBy(() -> workingHoursDateValidation.validate(appointment4AM))
                .withMessageContaining("Schedule date should be Monday through Friday / 7AM-7PM");
    }

    @Test
    @DisplayName("should not validate appointments at 7PM or later")
    public void validateWorkingHoursDateValidation7PM(){
        var appointmentDate7PM = LocalDateTime.now().withHour(19);
        var appointment7PM = new AppointmentData(null, null, appointmentDate7PM, null);

        Assertions.assertThatExceptionOfType(DataValidationException.class)
                .isThrownBy(() -> workingHoursDateValidation.validate(appointment7PM))
                .withMessageContaining("Schedule date should be Monday through Friday / 7AM-7PM");

        var appointmentDate21PM = LocalDateTime.now().withHour(21);
        var appointment21PM = new AppointmentData(null, null, appointmentDate21PM, null);

        Assertions.assertThatExceptionOfType(DataValidationException.class)
                .isThrownBy(() -> workingHoursDateValidation.validate(appointment21PM))
                .withMessageContaining("Schedule date should be Monday through Friday / 7AM-7PM");
    }

    @Test
    @DisplayName("should validate appointments withing working hours")
    public void validateWorkingHoursDateValidationWithinWorkingHours(){
        var appointmentDate7AM = LocalDateTime.now().withHour(7);
        var appointment7AM = new AppointmentData(null, null, appointmentDate7AM, null);

        Assertions.assertThatNoException().isThrownBy(() -> workingHoursDateValidation.validate(appointment7AM));

        var appointmentDate12PM = LocalDateTime.now().withHour(12);
        var appointment12PM = new AppointmentData(null, null, appointmentDate12PM, null);

        Assertions.assertThatNoException().isThrownBy(() -> workingHoursDateValidation.validate(appointment12PM));

        var appointmentDate3PM = LocalDateTime.now().withHour(15);
        var appointment3PM = new AppointmentData(null, null, appointmentDate3PM, null);

        Assertions.assertThatNoException().isThrownBy(() -> workingHoursDateValidation.validate(appointment3PM));

        var appointmentDate6PM = LocalDateTime.now().withHour(18);
        var appointment6PM = new AppointmentData(null, null, appointmentDate6PM, null);

        Assertions.assertThatNoException().isThrownBy(() -> workingHoursDateValidation.validate(appointment6PM));
    }

    @Test
    @DisplayName("should not validate appointments on sunday")
    public void validateWorkingHoursDateValidationSunday(){
        var appointmentDateSunday = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        var appointmentSunday = new AppointmentData(null, null, appointmentDateSunday, null);

        Assertions.assertThatExceptionOfType(DataValidationException.class)
                .isThrownBy(() -> workingHoursDateValidation.validate(appointmentSunday))
                .withMessageContaining("Schedule date should be Monday through Friday / 7AM-7PM");
    }

    @Test
    @DisplayName("should validate appointments on weekdays")
    public void validateWorkingHoursDateValidationWeekDay(){

        Arrays.stream(DayOfWeek.values())
                .filter(t -> ! t.equals(DayOfWeek.SUNDAY))
                .forEach(t -> {
                    var appointmentDateWeekDay = LocalDateTime.now().with(TemporalAdjusters.next(t));
                    var appointmentWeekDay = new AppointmentData(null, null, appointmentDateWeekDay, null);
                    Assertions.assertThatNoException().isThrownBy(() -> workingHoursDateValidation.validate(appointmentWeekDay));
                } );
    }
}