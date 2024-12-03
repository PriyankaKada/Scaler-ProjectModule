package com.scaler.authenticationservice.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@JsonDeserialize(as = User.class)
public class User extends  BaseModel{

    private String email;
    private String password;
    @ManyToMany
    private Set<Role> roles = new HashSet<>();
}
