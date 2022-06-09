package com.example.backend.services.implementations;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.backend.models.entities.Doctor;
import com.example.backend.models.entities.Patient;
import com.example.backend.models.entities.Person;
import com.example.backend.models.entities.UserModel;
import com.example.backend.models.repositories.DoctorRepository;
import com.example.backend.models.repositories.PatientRepository;
import com.example.backend.models.repositories.UserRepository;
import com.example.backend.services.interfaces.AuthServiceInterface;
import jdk.nashorn.internal.parser.Token;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
public class AuthService implements AuthServiceInterface {
    @Value("${jwtSecret}")
    private static String jwtSecret;

    static class Token {
        @Getter
        @Setter
        String email;
        @Getter
        @Setter
        String access;
        @Getter
        @Setter
        String refresh;

    }

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, PatientRepository patientRepository,
                DoctorRepository doctorRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Map<String, String> login(String email, String password) throws Exception {
        Objects.requireNonNull(email);
        Objects.requireNonNull(password);
        Map<String, String> token = new HashMap<>();
        Person person = userRepository.findByEmail(email);
        if (person == null) {
            throw new Exception("email not found");
        } else {
            // User user = new User(email, passwordEncoder.encode(password), Collections.singleton(new SimpleGrantedAuthority(person.getRole())));
            // authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
            String accessToken = JWT.create().withSubject(email).withExpiresAt(new Date(System.currentTimeMillis() + 10L * 60 * 3600 * 1000))
                    .withClaim("roles", String.valueOf(new SimpleGrantedAuthority(person.getRole().toUpperCase(Locale.ROOT))))
                    .sign(algorithm);
            String refreshToken = JWT.create().withSubject(email).withExpiresAt(new Date(System.currentTimeMillis() + 24L * 60 * 3600 * 1000))
                    .withClaim("roles",String.valueOf(new SimpleGrantedAuthority(person.getRole().toUpperCase(Locale.ROOT))))
                    .sign(algorithm);
            token.put("access", accessToken);
            token.put("refresh", refreshToken);
            token.put("userId", person.getId().toString());
            token.put("role", person.getRole());
            token.put("firstname", person.getFirstname());
            token.put("lastname", person.getLastname());
        }
        return token;
    }

    @Override
    public void signup(UserModel userModel) throws Exception {
        Person user = this.userRepository.findByEmail(userModel.getEmail());
        if (user != null) {
            throw new Exception("email already taken");
        } else {
            System.out.println(userModel.getFirstname());
            user = userModel.getRole().equals("doctor") ? new Doctor() : new Patient();
            user.setFirstname(userModel.getFirstname());
            user.setLastname(userModel.getLastname());
            user.setPassword(passwordEncoder.encode(userModel.getPassword()));
            user.setEmail(userModel.getEmail());
            user.setAvatarUrl("");
            user.setIsActive(true);
            user.setTelephone(userModel.getTelephone());
        }
        this.userRepository.save(user);
    }

    @Override
    public void logout(String refresh) throws Exception {

    }
}
