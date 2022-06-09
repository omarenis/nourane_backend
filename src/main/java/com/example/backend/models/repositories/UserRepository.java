package com.example.backend.models.repositories;


import com.example.backend.models.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Person, Long> {
    public Person findByEmail(String email);
}
