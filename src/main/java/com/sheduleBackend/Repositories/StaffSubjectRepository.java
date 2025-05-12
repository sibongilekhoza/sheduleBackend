package com.sheduleBackend.Repositories;

import com.sheduleBackend.Models.Staff;
import com.sheduleBackend.Models.StaffSubject;
import com.sheduleBackend.Models.Subject;
import com.sheduleBackend.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffSubjectRepository extends JpaRepository<StaffSubject, Long> {
     Optional<StaffSubject>findByStaff(Staff staff);
     StaffSubject findByStaffAndSubjectId(Staff staff,Subject subject);
     List<StaffSubject> findAllByStaff(Staff staff);
}
