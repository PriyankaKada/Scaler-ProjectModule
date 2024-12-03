package com.scaler.authenticationservice.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonDeserialize(as= Role.class)
@Entity
@Table(name = "roles")
public class Role extends BaseModel {

    private String role;
}
