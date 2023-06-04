package com.alura.djonatah.medvollapi.domain.Doctor;

import com.alura.djonatah.medvollapi.domain.common.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DoctorData(@NotBlank
                         String name,
                         @NotBlank
                         @Pattern(regexp = "\\d{4,6}")
                         String register,
                         @NotBlank
                         String phone,
                         @NotBlank
                         @Email
                         String email,
                         @NotNull
                         Speciality speciality,
                         @NotNull
                         @Valid
                         AddressData address) {
}
