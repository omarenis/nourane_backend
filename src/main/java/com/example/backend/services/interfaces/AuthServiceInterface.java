package com.example.backend.services.interfaces;


import com.example.backend.models.entities.Person;
import com.example.backend.models.entities.UserModel;

import java.util.Map;

public interface AuthServiceInterface {
    public Map<String, String> login(String email, String password) throws Exception;
    void signup(UserModel user) throws Exception;
    void logout(String refresh) throws Exception;
}
