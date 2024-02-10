package com.api.loginApi.controller;

import com.api.loginApi.infra.exceptions.dto.ResponseMessageDTO;
import com.api.loginApi.infra.security.TokenService;
import com.api.loginApi.infra.security.dto.TokenDTO;
import com.api.loginApi.model.login.dto.LoginDTO;
import com.api.loginApi.model.users.Users;
import com.api.loginApi.model.users.UsersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO data) {
        String email = data.email();
        String password = data.password();
        var user = repository.findByEmail(email);
        if (user == null) {
            return ResponseEntity.status(404).body(new ResponseMessageDTO("User not found"));
        }
        String userPassword = user.getPassword();
        if (encoder.matches(password, userPassword)) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            Authentication auth = manager.authenticate(authToken);
            String jwt = tokenService.generate((Users) auth.getPrincipal());
            return ResponseEntity.ok(new TokenDTO(jwt));
        }
        return ResponseEntity.status(500).body(new ResponseMessageDTO("Invalid password!"));
    }

}
