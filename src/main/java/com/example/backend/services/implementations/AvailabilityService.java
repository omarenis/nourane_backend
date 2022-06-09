package com.example.backend.services.implementations;


import com.example.backend.models.entities.Availability;
import com.example.backend.models.entities.Doctor;
import com.example.backend.models.repositories.AvailabilityRepository;
import com.example.backend.models.repositories.DoctorRepository;
import com.example.backend.services.interfaces.AvailabilityServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvailabilityService implements AvailabilityServiceInterface {
    private final AvailabilityRepository availabilityRepository;
    private final DoctorRepository doctorRepository;
    @Autowired
    public AvailabilityService(AvailabilityRepository availabilityRepository, DoctorRepository doctorRepository)
    {
        this.availabilityRepository = availabilityRepository;
        this.doctorRepository = doctorRepository;
    }
    @Override
    public List<Availability> list() {
        return this.availabilityRepository.findAll();
    }

    public Availability create(Availability entity, User doctor) throws Exception {

        Optional<Doctor> doctorModel = this.doctorRepository.findByEmail(doctor.getUsername());
        if(!doctorModel.isPresent())
        {
            throw new Exception("doctor not found");
        }
        else
        {
            Availability availability = availabilityRepository.findByDateTimeStartBetween(entity.getDateTimeStart(),
                    entity.getDateTimeEnd());
            if(availability != null)
            {
                throw new Exception("voud ne pouvez pas utiliser cette date et cette heure");
            }
            availability = availabilityRepository.findByDateTimeEndBetween(entity.getDateTimeStart(),
                    entity.getDateTimeEnd());
            if (availability != null)
            {
                throw new Exception("vous ne pouver prener cette date");
            }
            entity.setDoctor(doctorModel.get());
            return availabilityRepository.saveAndFlush(entity);
        }
    }

    @Override
    public Optional<Availability> findById(Long id) {
        return availabilityRepository.findById(id);
    }

    @Override
    public Availability update(Long id, Availability entity) {
        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<Availability> availability = availabilityRepository.findById(id);
        availability.ifPresent(this.availabilityRepository::delete);
    }

    @Override
    public Availability create(Availability entity, Doctor doctor) throws Exception {
        return null;
    }

    public List<Availability> findAll()
    {
        return availabilityRepository.findAll();
    }
    public List<Availability> findByDoctorId(Long doctorId)
    {
        return availabilityRepository.findByDoctorId(doctorId);
    }
}
