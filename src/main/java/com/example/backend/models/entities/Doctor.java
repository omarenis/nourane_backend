package com.example.backend.models.entities;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;


@Entity(name="doctors")
@DiscriminatorValue("doctor")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Doctor extends Person {
    @Column(name = "speciality") @Getter @Setter @NotNull private String speciality;
    @OneToMany @Getter @Setter List<Availability>  availabilities;
}
