package com.api.loginApi.model.users;

import com.api.loginApi.model.address.Address;
import com.api.loginApi.model.users.dto.UpdateUserDTO;
import com.api.loginApi.model.users.dto.UsersDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;

@Entity(name="Users")
@Table(name="users")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String username;
    private boolean active;

    @Embedded
    private Address address;

    public Users (UsersDTO data) {
        this.name = data.name();
        this.email = data.email();
        this.username = data.username();
        this.password = hashPassword(data.password());
        this.address = new Address(data.address());
        this.active = true;
    }


    private String hashPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode(password);
        return hash;
    }

    public void updateData(UpdateUserDTO data) {
        if (data.address() != null) {
            this.address.updateData(data.address());
        }
        if (data.name() != null) {
            this.name = data.name();
        }
        if (data.email() != null) {
            this.email = data.email();
        }
        if (data.username() != null) {
            this.username = data.username();
        }
    }

    public void inactive() {
        this.active = false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
