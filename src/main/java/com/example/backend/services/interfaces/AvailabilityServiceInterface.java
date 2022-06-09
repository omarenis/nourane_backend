package com.example.backend.services.interfaces;


import com.example.backend.models.entities.Availability;
import com.example.backend.models.entities.Doctor;

import java.util.List;
import java.util.Optional;

public interface AvailabilityServiceInterface {

    public List<Availability> list();

    public Optional<Availability> findById(Long id);

    public Availability update(Long id, Availability entity);

    public void delete(Long id);

    Availability create(Availability entity, Doctor doctor) throws Exception;
}
