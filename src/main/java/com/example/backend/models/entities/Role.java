package com.example.backend.models.entities;

import lombok.*;

import javax.persistence.*;

@Entity(name = "roles")
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Getter private Long id;
    @Column(name = "name") @Getter @Setter @NonNull private String name;
}
