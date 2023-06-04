package com.alura.djonatah.medvollapi.domain.common;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record AddressData(
                        @NotEmpty
                        String street,
                        @NotEmpty
                        String zone,
                        @NotEmpty
                        @Pattern(regexp = "\\d{5}")
                        String zip,
                        @NotEmpty
                        String city,
                        @NotEmpty
                        String state,
                        String additionalInfo,
                        String number) {
}
