package com.example.backend.services.interfaces;


import com.example.backend.models.entities.Person;
import com.example.backend.models.entities.UserModel;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public interface UserCrudServiceInterface {
    final Path root = Paths.get("uploads");
    public void saveFile(MultipartFile file);
    public Person save(UserModel user);
    public List<Person> list();
    public Person update(Long id, Person user);
    public void delete(Long id);

    public Optional<? extends Person> findById(Long id);
}
