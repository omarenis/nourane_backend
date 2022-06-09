package com.example.backend.controllers;


import com.example.backend.models.entities.Person;
import com.example.backend.models.entities.UserModel;
import com.example.backend.services.implementations.FileService;
import com.example.backend.services.implementations.UserCrudService;
import com.example.backend.services.interfaces.UserCrudServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserCrudController {
    private UserCrudServiceInterface userCrudService;
    private final FileService fileService;

    @Autowired
    public UserCrudController(UserCrudService userCrudService, FileService fileService)
    {
        this.userCrudService = userCrudService;
        this.fileService = fileService;
    }

    @GetMapping("/{id}")
    Optional<? extends Person> getById(@PathVariable Long id)
    {
        return this.userCrudService.findById(id);
    }
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Person update(UserModel userModel)
    {
        return null;
    }

}
