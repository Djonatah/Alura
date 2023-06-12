package com.alura.djonatah.medvollapi.domain.model.user;

import jakarta.validation.constraints.NotBlank;

public record UserData(
        @NotBlank
        String username,
        @NotBlank
        String password) {
}
