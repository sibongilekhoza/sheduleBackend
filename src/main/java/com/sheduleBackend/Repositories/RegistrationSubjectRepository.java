package com.sheduleBackend.Repositories;

import com.sheduleBackend.Models.RegistrationSubject;
import com.sheduleBackend.Models.Registrations;
import com.sheduleBackend.Models.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationSubjectRepository extends CrudRepository<RegistrationSubject,Long> {
    List<RegistrationSubject> findAllBySubject(Subject subject);
    List<RegistrationSubject> findAllByRegistrations(Registrations registrations);
}
