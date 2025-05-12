package com.sheduleBackend.Repositories;

import com.sheduleBackend.Models.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student,Long> {

    long count();
}
