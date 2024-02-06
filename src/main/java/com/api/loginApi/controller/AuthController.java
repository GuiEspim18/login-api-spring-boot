package com.api.loginApi.controller;

import com.api.loginApi.model.login.dto.LoginDTO;
import com.api.loginApi.model.users.UsersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private UsersRepository repository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @PostMapping("/login")
    public String login(@RequestBody @Valid LoginDTO data) {
        String email = data.email();
        String password = data.password();
        var user = repository.findByEmail(email);
        if (user == null) {
            return "User not found!";
        }
        String userPassword = user.getPassword();
        if (encoder.matches(password, userPassword)) {
            return "Hello " + user.getUsername();
        }
        return "Invalid password!";
    }

}
