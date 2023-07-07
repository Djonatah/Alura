package com.alura.djonatah.medvollapi.domain.model.appointment.validation;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class AppointmentWorkingHoursDateValidation {
    public boolean isValid(LocalDateTime appointmentDate){
        DayOfWeek dayOfWeek = appointmentDate.getDayOfWeek();
        if(dayOfWeek.equals(DayOfWeek.SUNDAY))
            return false;

        int hour = appointmentDate.getHour();
        if (hour < 7 || hour > 18)
            return false;
        return true;
    }
}
