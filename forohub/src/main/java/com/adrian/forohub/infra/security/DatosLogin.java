package com.adrian.forohub.infra.security;

import jakarta.validation.constraints.NotBlank;

public record DatosLogin(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
