package com.example.backend.controllers;

import com.example.backend.models.entities.Availability;
import com.example.backend.models.entities.AvailabilityModel;
import com.example.backend.models.entities.Person;
import com.example.backend.models.repositories.UserRepository;
import com.example.backend.services.implementations.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/availabilities")
public class AvailabilityController {

    private final AvailabilityService availabilityService;
    private final UserRepository userRepository;

    @Autowired
    AvailabilityController(AvailabilityService availabilityService, UserRepository userRepository) {
        this.availabilityService = availabilityService;
        this.userRepository = userRepository;
    }

    private final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    @GetMapping("")
    public List<Availability> find(@RequestParam AvailabilityModel availabilityModel) throws Exception {
        User user = (User) this.auth.getPrincipal();
        Person person = userRepository.findByEmail(user.getUsername());
        if (person != null) {
            if (person.getRole().equals("doctor"))
                return this.availabilityService.findByDoctorId(person.getId());
            else
                return this.availabilityService.findByDoctorId(availabilityModel.getDoctor());
        } else {
            throw new Exception("bad user");
        }
    }

    @PostMapping("")
    public Availability create(@RequestBody Availability availability) throws Exception {
        User user = (User) auth.getPrincipal();
        return availabilityService.create(availability, user);
    }
}
