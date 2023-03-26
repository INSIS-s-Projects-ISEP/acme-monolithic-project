package com.isep.acme.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long userId;

    @Email
    @Column(unique = true)
    private String username;
    private String password;
    private String fullName;

    @ElementCollection
    private Set<Role> authorities = new HashSet<>();

    @NotBlank
    @Size(min = 9, max = 9, message = "NIF must be 9 characters.")
    @Column(unique = true)
    private String nif;
    
    @NotBlank
    private String morada;

    public User(String username, String password, String fullName, String nif, String morada){
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.morada = morada;
        setNif(nif);
    }

    public void addAuthority(Role role) {
        authorities.add(role);
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

