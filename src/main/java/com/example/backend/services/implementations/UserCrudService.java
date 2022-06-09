package com.example.backend.services.implementations;


import com.example.backend.models.entities.Doctor;
import com.example.backend.models.entities.Patient;
import com.example.backend.models.entities.Person;
import com.example.backend.models.entities.UserModel;
import com.example.backend.models.repositories.DoctorRepository;
import com.example.backend.models.repositories.PatientRepository;
import com.example.backend.models.repositories.UserRepository;
import com.example.backend.services.interfaces.UserCrudServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class UserCrudService implements UserCrudServiceInterface {
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public UserCrudService(UserRepository userRepository, DoctorRepository doctorRepository,
                           PatientRepository patientRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void saveFile(MultipartFile file) {

    }

    @Override
    public Person save(UserModel userModel) {
        Person user = userModel.getRole().equals("doctor") ? new Doctor() : new Patient();
        user.setEmail(userModel.getEmail());
        user.setFirstname(userModel.getFirstname());
        user.setLastname(userModel.getLastname());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        user.setAvatarUrl("");
        user.setIsActive(true);
        if(user.getRole().equals("doctor"))
        {
            assert user instanceof Doctor;
            ((Doctor) user).setSpeciality(userModel.getSpeciality());
        }
        return this.userRepository.save(user);
    }

    @Override
    public Optional<? extends Person> findById(Long id)
    {
        Optional<Person> user = userRepository.findById(id);
        if(user.isPresent())
        {
            Person userEntity = user.get();
            if(userEntity.getRole().equals("patient"))
            {
                return patientRepository.findById(userEntity.getId());
            }
            if(userEntity.getRole().equals("doctor"))
            {
                return doctorRepository.findById(userEntity.getId());
            }
        }
        return user;
    }

    @Override
    public List<Person> list() {
        return userRepository.findAll();
    }

    @Override
    public Person update(Long id, Person user) {

        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<Person> user = userRepository.findById(id);
        user.ifPresent(userRepository::delete);
    }
}
