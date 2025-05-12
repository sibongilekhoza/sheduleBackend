package com.sheduleBackend.Repositories;

import com.sheduleBackend.Models.StaffSubject;
import com.sheduleBackend.Models.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TimetableRepository extends JpaRepository<Timetable,Long> {

    List<Timetable> findALLByStaffSubjectIn(List<StaffSubject> staffSubject);
}
