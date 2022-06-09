package com.example.backend.security;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


public class RequestBodyMapper
{
    @Getter
    @Setter
    @NonNull private  String email;
    @Getter @Setter @NonNull private String password;

    RequestBodyMapper(){}

    RequestBodyMapper(String email, String password)
    {
        this.email = email;
        this.password = password;
    }

}
