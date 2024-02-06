package com.api.loginApi.controller;

import com.api.loginApi.model.users.Users;
import com.api.loginApi.model.users.UsersRepository;
import com.api.loginApi.model.users.dto.UserDTO;
import com.api.loginApi.model.users.dto.UsersDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UsersRepository repository;

    @GetMapping
    public List<UserDTO> get() {
        return repository.findAll().stream().map(UserDTO::new).toList();
    }

    @PostMapping
    @Transactional
    public void create(@RequestBody @Valid UsersDTO data) {
        var user = new Users(data);
        repository.save(user);
    }

}
