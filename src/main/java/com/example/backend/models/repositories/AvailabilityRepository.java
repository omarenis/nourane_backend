package com.example.backend.models.repositories;


import com.example.backend.models.entities.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    public List<Availability> findByDoctorId(Long doctorId);
    public Availability findByDateTimeStart(Date dateTimeStart);
    public Availability findByDateTimeEnd(Date dateTimeEnd);

    public Availability findByDateTimeStartBetween(Date dateTimeStart, Date DateTimeEnd);
    public Availability findByDateTimeEndBetween(Date dateTimeStart, Date dateTimeEnd);

}
