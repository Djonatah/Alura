package com.alura.djonatah.medvollapi.domain.model.patient;

import com.alura.djonatah.medvollapi.domain.model.common.AddressData;
import jakarta.validation.constraints.NotNull;

public record PatientUpdateData(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressData address) {
}
