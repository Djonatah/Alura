package com.alura.djonatah.medvollapi.domain.Patient;

import com.alura.djonatah.medvollapi.domain.common.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PatientData(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String phone,
        @NotBlank
        String ssn,
        @NotNull
        @Valid
        AddressData address
    ){
}
