package com.example.backend.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class  Availability {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(name = "dateTimeStart") @Getter @Setter private Date dateTimeStart;
    @Column(name = "dateTimeEnd") @Getter @Setter private Date dateTimeEnd;
    @ManyToOne @Getter @Setter private Doctor doctor;
    @OneToMany @Getter @Setter private List<Appointment> appointments;
}
