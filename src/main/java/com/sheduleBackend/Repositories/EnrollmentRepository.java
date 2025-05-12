package com.sheduleBackend.Repositories;

import com.sheduleBackend.Models.Enrollment;
import com.sheduleBackend.Models.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnrollmentRepository extends CrudRepository<Enrollment ,Long> {
    Optional<Enrollment> findByStudent(Student student);
}
