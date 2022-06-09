package com.example.backend.models.entities;

import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class AvailabilityModel {
    @Getter @Setter  @NonNull  private Date dateTimeStart;
    @Getter @Setter @NonNull private Date dateTimeEnd;
    @Getter @Setter @NonNull  private Long doctor;
    @Getter @Setter private List<Appointment> appointments;
}
