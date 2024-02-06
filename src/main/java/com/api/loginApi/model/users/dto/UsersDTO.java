package com.api.loginApi.model.users.dto;

import com.api.loginApi.model.address.dto.AddressDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

public record UsersDTO(
        @NotBlank
        String name,

        @NotBlank
        String username,

        @NotBlank
        @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message="Invalid email")
        String email,

        @NotBlank
        String password,

        Date last_login,
        Date created_at,

        @Valid
        @NotNull
        AddressDTO address
) {
}
