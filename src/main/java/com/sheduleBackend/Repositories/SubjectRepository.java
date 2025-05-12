package com.sheduleBackend.Repositories;

import com.sheduleBackend.Models.Course;
import com.sheduleBackend.Models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
 Optional<Subject> findBySubjectName(String name);

 Optional<Subject> findBySubjectCode(String subjectCode);
 public Subject findBySubjectId(Long subject_id);
 public List<Subject> findByCourse(Course course);

 long count();
}
