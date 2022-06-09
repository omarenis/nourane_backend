package com.example.backend.controllers;


import com.example.backend.models.entities.Doctor;
import com.example.backend.models.entities.UserModel;
import com.example.backend.models.repositories.DoctorRepository;
import com.example.backend.security.RequestBodyMapper;
import com.example.backend.services.implementations.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin("*")
public class AuthController {
    @Value("${jwtSecret}")
    private static String jwtSecret;
    private final AuthService authService;
    private final DoctorRepository doctorRepository;
    @Autowired
    AuthController(AuthService authService, DoctorRepository doctorRepository)
    {
        this.authService = authService;
        this.doctorRepository = doctorRepository;
    }

    @CrossOrigin("*")
    @RequestMapping(path="/api/login", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE,
    produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> login(@RequestBody RequestBodyMapper requestBodyMapper)
    {
        try
        {
            return new ResponseEntity<>(this.authService.login(requestBodyMapper.getEmail(), requestBodyMapper.getPassword()), HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> errorMessage = new HashMap<>();
            errorMessage.put("message", e.getMessage());

            HttpStatus status = e.getMessage().equals("email not found") ? HttpStatus.NOT_FOUND : HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity<>(errorMessage, status);
        }
    }

    @RequestMapping(path = "/api/signup", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE,
    produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> signup(@RequestBody UserModel userModel)
    {
        Map<String, String> data = new HashMap<>();
        try {
            authService.signup(userModel);
            return new ResponseEntity<>(HttpStatus.CREATED );
        } catch (Exception e) {
            data.put("message", e.getMessage());
            if(e.getMessage().contains("taken"))
            {
                return new ResponseEntity<>(data, HttpStatus.FORBIDDEN);
            }
            return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @RequestMapping(path = "/doctors", method = RequestMethod.GET)
    public List<Doctor> getAllDoctors(@RequestParam(required = false) String speciality)
    {
        return speciality == null ? this.doctorRepository.findAll() : this.doctorRepository.findBySpecialityContains(speciality);
    }
}
