package com.api.loginApi.model.login.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LoginDTO(
        @NotBlank
        @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message="Invalid email")
        String email,

        @NotBlank
        String password
) {
}
