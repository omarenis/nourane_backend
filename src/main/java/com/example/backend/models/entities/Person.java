package com.example.backend.models.entities;

import com.sun.istack.NotNull;
import lombok.*;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

@Entity(name="users")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name ="typeUser")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Person {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Getter private Long id;
    @Column(name = "firstname") @NotNull @Getter @Setter private String firstname;
    @Column(name = "lastname") @NotNull @Getter @Setter private String lastname;
    @Column(name="email") @NotNull @Getter @Setter private String email;
    @Column(name="password") @NotNull @Getter @Setter private String password;
    @Column(name="avatar") @NotNull @Getter @Setter private String avatarUrl = "";
    @Column(name = "is_active") @NonNull @Getter @Setter private Boolean isActive = true;
    @Column(name = "telephone") @NonNull @Getter @Setter private String telephone;
    @Column(name = "role") @NonNull @Getter @Setter private String role;
    @ManyToMany(fetch = FetchType.EAGER) private Collection<Role> roles;
//    @Transient private @Getter @Setter Collection<GrantedAuthority> grantedAuthorities;
//
//    public Collection<GrantedAuthority> getAuthorities() {
//            this.grantedAuthorities.add(new SimpleGrantedAuthority(this.role.toUpperCase(Locale.ROOT)));
//            return this.grantedAuthorities;
//    }
}
