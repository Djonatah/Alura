package com.alura.djonatah.medvollapi.domain.model.doctor;

import com.alura.djonatah.medvollapi.domain.model.common.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DoctorRegisterData(@NotBlank(message = "name must not be blank")
                         String name,
                         @NotBlank(message = "register must not be blank")
                         @Pattern(regexp = "\\d{4,6}", message = "register needs to have 4-6 digits")
                         String register,
                         @NotBlank(message = "phone must not be blank")
                         String phone,
                         @NotBlank(message = "Email must not be blank")
                         @Email(message = "Bad email format")
                         String email,
                         @NotNull(message = "Speciality must not be blank")
                         Speciality speciality,
                         @NotNull(message = "Address is required")
                         @Valid
                         AddressData address) {
}
