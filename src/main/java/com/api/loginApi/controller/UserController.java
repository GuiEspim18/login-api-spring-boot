package com.api.loginApi.controller;

import com.api.loginApi.model.users.dto.UsersDTO;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @GetMapping
    public String get() {
        return "Hello World!";
    }

    @PostMapping
    @Transactional
    public void create(@RequestBody @Valid UsersDTO data) {
        System.out.println(data);
    }

}
