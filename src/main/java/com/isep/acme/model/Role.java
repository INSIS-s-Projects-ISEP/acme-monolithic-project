package com.isep.acme.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import org.springframework.security.core.GrantedAuthority;

@Getter
@AllArgsConstructor
public class Role implements GrantedAuthority {

    public static final String Admin = "Admin";
    public static final String Mod = "Mod";
    public static final String RegisteredUser = "RegisteredUser";

    private String authority;
    
}
