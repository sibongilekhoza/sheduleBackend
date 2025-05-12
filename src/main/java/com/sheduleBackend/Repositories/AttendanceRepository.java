package com.sheduleBackend.Repositories;


import com.sheduleBackend.Models.Attendance;
import com.sheduleBackend.Models.RegistrationSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
    long countByRegistrationSubjectAndRecord(RegistrationSubject registrationSubject, String record);
    long countByRegistrationSubject(RegistrationSubject registrationSubject);
}
