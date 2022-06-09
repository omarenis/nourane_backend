package com.example.backend.models.repositories;


import com.example.backend.models.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findBySpecialityContains(String speciality);

    Optional<Doctor> findByEmail(String email);
}
