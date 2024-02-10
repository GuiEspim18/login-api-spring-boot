package com.api.loginApi.model.users.dto;

import com.api.loginApi.model.users.Users;

public record UsersDetailDTO(
        Long id,
        String email,
        String name,
        String username
) {
    public UsersDetailDTO(Users users) {
        this(users.getId(), users.getEmail(), users.getName(), users.getUsername());
    }
}
