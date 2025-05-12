package com.sheduleBackend.BusinessServices.impl;

import com.sheduleBackend.Api.Response.Response;
import com.sheduleBackend.Enums.Periods;
import com.sheduleBackend.Enums.WeekDays;
import com.sheduleBackend.Exceptions.SubjectException;
import com.sheduleBackend.Models.Staff;
import com.sheduleBackend.Models.StaffSubject;
import com.sheduleBackend.Models.Subject;
import com.sheduleBackend.Models.Timetable;
import com.sheduleBackend.Repositories.StaffSubjectRepository;
import com.sheduleBackend.Repositories.SubjectRepository;
import com.sheduleBackend.Repositories.TimetableRepository;
import com.sheduleBackend.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class TimeTableImpl {

    @Autowired
    private TimetableRepository timetableRepository;
    @Autowired
    private StaffSubjectRepository staffSubjectRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Response createTimetable(long  staffId, long subjectId , WeekDays weekDays, Periods period ){

       Optional<Staff> exists = userRepository.findByStaffNo(staffId);
       Optional<Subject> sub = subjectRepository.findById(subjectId);

        if (exists.isPresent() && sub.isPresent()) {
            Optional<StaffSubject> staffSubject = staffSubjectRepository
                    .findByStaff(exists.get());
            if (staffSubject.isPresent()) {
                Timetable timetable = new Timetable();
                //check if the lecturer is not booked already for that period
                //Lets disable the select when lecturer is booked

                timetable.setPeriods(period);
                timetable.setWeekDays(weekDays);
                timetable.setStaffSubject(staffSubject.get());
                System.out.println(timetable.getWeekDays());
                timetableRepository.save(timetable);
            }

        }else {
            throw  new IllegalStateException("Unable To Create Schedule!");
        }

        return new Response("Added to timetable" , "200");
    }

}
