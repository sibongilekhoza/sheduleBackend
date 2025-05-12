package com.sheduleBackend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sheduleBackend.Models.Course;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course , Long> {
    Optional<Course> findByCourseName(String courseName);

    Optional<Course> findByCourseCode(String courseCode);
    public  Course findByCourseId(long course);


    long count();
}
