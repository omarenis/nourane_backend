package com.example.backend.services.implementations;

import com.example.backend.models.entities.Appointment;
import com.example.backend.models.repositories.AppointmentRepository;
import com.example.backend.models.repositories.DoctorRepository;
import com.example.backend.models.repositories.PatientRepository;
import com.example.backend.services.interfaces.AbstractGenericServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppointmentService implements AbstractGenericServiceInterface<Appointment> {

    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(DoctorRepository doctorRepository, PatientRepository patientRepository,
                              AppointmentRepository appointmentRepository)
    {
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> list() {
        return this.appointmentRepository.findAll();
    }

    public List<Appointment> findBySubject(String subject) {
        return this.appointmentRepository.findBySubject(subject);
    }

    public List<Appointment> findByDate(Date creationDate) {
        return appointmentRepository.findByDateCreation(creationDate);
    }

    @Override
    public Appointment create(Appointment appointment) {
        return appointmentRepository.saveAndFlush(appointment);
    }
    @Override
    public Appointment findById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    @Override
    public Appointment update(Long id, Appointment appointment) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
