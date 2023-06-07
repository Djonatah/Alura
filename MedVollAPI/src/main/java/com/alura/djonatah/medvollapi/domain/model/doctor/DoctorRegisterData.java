package com.alura.djonatah.medvollapi.domain.model.doctor;

import com.alura.djonatah.medvollapi.domain.model.common.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DoctorRegisterData(@NotBlank
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
