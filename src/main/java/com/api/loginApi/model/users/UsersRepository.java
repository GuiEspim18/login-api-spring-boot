package com.api.loginApi.model.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);
    List<Users> findAllByActiveTrue();
    UserDetails findByUsername(String username);
}
