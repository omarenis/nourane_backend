package com.example.backend.models.repositories;


import com.example.backend.models.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findBySubject(String subject);
    List<Appointment> findByAvailability_Id(Long availability);
    List<Appointment> findByDateCreation(Date dateCreation);

    List<Appointment> findByPatientId(Long patientId);
}
