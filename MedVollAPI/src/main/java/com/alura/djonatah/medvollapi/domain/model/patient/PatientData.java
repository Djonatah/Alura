package com.alura.djonatah.medvollapi.domain.model.patient;

import com.alura.djonatah.medvollapi.domain.model.common.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PatientData(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String phone,
        @NotBlank
        @Pattern(regexp = "[0-9\\-]{11}", message = "invalid format, should be xxx-xxx-xxx")
        @Valid
        String ssn,
        @NotNull
        @Valid
        AddressData address
    ){
}
