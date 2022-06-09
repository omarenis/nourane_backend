package com.example.backend.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Appointment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)  @Getter @Setter private Long id;
    @Getter @Setter @ManyToOne @JoinColumn(name = "patient") private Patient patient;
    @Column(name = "subject") @Getter @Setter private String subject;
    @Column(name = "creation_date") @Getter @Setter private Date dateCreation = new Date();
    @ManyToOne @JoinColumn(name = "availability") @Getter @Setter private Availability availability;
    @Getter @Setter private boolean status;
}
