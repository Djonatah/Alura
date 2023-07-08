package com.alura.djonatah.medvollapi.domain.model.appointment.validation;

import com.alura.djonatah.medvollapi.domain.model.appointment.AppointmentData;
import com.alura.djonatah.medvollapi.domain.model.appointment.DataValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class AppointmentWorkingHoursDateValidation implements AppointmentScheduleValidation{

    public void validate(AppointmentData appointmentData){
        LocalDateTime appointmentDate = appointmentData.date();
        var dayOfWeek = appointmentDate.getDayOfWeek();
        var hour = appointmentDate.getHour();
        if (dayOfWeek.equals(DayOfWeek.SUNDAY) || hour < 7 || hour > 18)
            throw new DataValidationException("Unable to schedule appointment. Schedule date should be Monday through Friday / 7AM-7PM");

    }
}
