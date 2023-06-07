package com.alura.djonatah.medvollapi.domain.model.doctor;

import com.alura.djonatah.medvollapi.domain.model.common.AddressData;
import jakarta.validation.constraints.NotNull;

public record DoctorUpdateData(
        @NotNull
        Long id,
        String phone,
        String email,
        AddressData address ) {
}
