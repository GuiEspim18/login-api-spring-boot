package com.api.loginApi.controller;

import com.api.loginApi.model.users.Users;
import com.api.loginApi.model.users.UsersRepository;
import com.api.loginApi.model.users.dto.UpdateUserDTO;
import com.api.loginApi.model.users.dto.UserDTO;
import com.api.loginApi.model.users.dto.UsersDTO;
import com.api.loginApi.model.users.dto.UsersDetailDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UsersRepository repository;

    @GetMapping
    public ResponseEntity<List<UserDTO>> get() {
        List<UserDTO> users = repository.findAllByActiveTrue().stream().map(UserDTO::new).toList();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> create(@RequestBody @Valid UsersDTO data, UriComponentsBuilder uriBuilder) {
        Users user = new Users(data);
        repository.save(user);
        URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsersDetailDTO(user));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> update (@RequestBody @Valid UpdateUserDTO data) {
        Users user = repository.getReferenceById(data.id());
        user.updateData(data);
        return ResponseEntity.ok(new UsersDetailDTO(user));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Users user = repository.getReferenceById(id);
        user.inactive();
        return ResponseEntity.noContent().build();
    }

}
