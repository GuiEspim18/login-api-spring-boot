package com.api.loginApi.model.address.dto;

import jakarta.validation.constraints.NotBlank;

public record AddressDTO(
        @NotBlank
        String street,

        @NotBlank
        String zipcode,

        String number,
        String compliment,

        @NotBlank
        String neighborhood,

        @NotBlank
        String city,

        @NotBlank
        String uf
) {
}
