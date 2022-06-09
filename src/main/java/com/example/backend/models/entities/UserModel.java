package com.example.backend.models.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.File;


public class UserModel {
    @Getter private Long id;
    @Getter @Setter private String firstname;
    @Getter @Setter private String lastname;
    @Getter @Setter private String email;
    @Getter @Setter private String password;
    @Getter @Setter private String avatarUrl;
    @Getter @Setter private String role;
    @Getter @Setter private String speciality = null;
    @Getter @Setter private Boolean isActive = true;
    @Getter @Setter private String telephone;
    @Getter @Setter private File avatarFile;
    public UserModel(){}

    public UserModel(String firstname, String lastname, String email, String password, String avatarUrl, String role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.avatarUrl = avatarUrl;
        this.role = role;
    }
    public UserModel(String firstname, String lastname, String email, String password, String avatarUrl, String role,
                     File avatarFile) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.avatarUrl = avatarUrl;
        this.role = role;
        this.avatarFile = avatarFile;
    }
    public UserModel(String firstname, String lastname, String email, String password, String avatarUrl, String role,
                     String speciality) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.avatarUrl = avatarUrl;
        this.role = role;
        this.speciality = speciality;
    }
}
