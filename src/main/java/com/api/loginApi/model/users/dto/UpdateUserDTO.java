package com.api.loginApi.model.users.dto;

import com.api.loginApi.model.address.dto.AddressDTO;
import jakarta.validation.constraints.NotNull;

public record UpdateUserDTO(
        @NotNull
        Long id,

        String name,
        String email,
        String username,
        AddressDTO address
) {
}
