package com.api.loginApi.model.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="Users")
@Table(name="users")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;



}
