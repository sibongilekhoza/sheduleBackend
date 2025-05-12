package com.sheduleBackend.Repositories;

import com.sheduleBackend.Models.Enrollment;
import com.sheduleBackend.Models.Registrations;
import com.sheduleBackend.Models.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegistrationRepository extends CrudRepository<Registrations,Long> {
    Registrations findByEnrollment(Enrollment enrollment);
}
