package com.alura.djonatah.medvollapi.domain.model.common;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record AddressData(
                        @NotBlank
                        String street,
                        @NotBlank
                        String zone,
                        @NotBlank
                        @Pattern(regexp = "\\d{5}")
                        String zip,
                        @NotBlank
                        String city,
                        @NotBlank
                        String state,
                        String additionalInfo,
                        String number) {
}
