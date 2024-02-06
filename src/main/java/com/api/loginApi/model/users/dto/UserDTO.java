package com.api.loginApi.model.users.dto;

import com.api.loginApi.model.users.Users;

public record UserDTO(
        Long id,
        String name,
        String username,
        String email
) {
    public UserDTO(Users data) {
        this(data.getId(), data.getName(), data.getUsername(), data.getEmail());
    }
}
