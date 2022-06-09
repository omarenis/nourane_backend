package com.example.backend.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity(name="patients")
@DiscriminatorValue("patient")
public class Patient extends Person{
    @OneToMany @Getter @Setter private List<Appointment> appointments;
}
