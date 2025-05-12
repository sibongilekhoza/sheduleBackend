package com.sheduleBackend.BusinessServices.impl;

import com.sheduleBackend.Api.Requests.CreateTimeTableRequest;
import com.sheduleBackend.Api.Requests.DayTimeSubjectRequest;
import com.sheduleBackend.Api.Response.Response;
import com.sheduleBackend.Enums.Periods;
import com.sheduleBackend.Enums.WeekDays;
import com.sheduleBackend.Models.*;
import com.sheduleBackend.Models.Dto.requests.SubjectRequestDto;
import com.sheduleBackend.Models.Dto.responses.StaffResponseDto;
import com.sheduleBackend.Repositories.StaffSubjectRepository;
import com.sheduleBackend.Repositories.SubjectRepository;
import com.sheduleBackend.Repositories.TimetableRepository;
import com.sheduleBackend.Repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sheduleBackend.BusinessServices.Interface.AdminService;
import com.sheduleBackend.BusinessServices.Interface.CourseService;
import com.sheduleBackend.BusinessServices.Interface.LecturerService;
import com.sheduleBackend.BusinessServices.Interface.SubjectService;
import com.sheduleBackend.Models.Dto.requests.CourseRequestDto;
import com.sheduleBackend.Models.Dto.requests.StaffRequestDto;
import com.sheduleBackend.Models.Dto.responses.SubjectResponseDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private LecturerService lecturerService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TimeTableImpl timeTable;
    @Autowired
    private StaffSubjectRepository staffSubjectRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TimetableRepository timetableRepository;


    @Override
    public Course createCourse(CourseRequestDto courseRequestDto) {
        return courseService.createCourse(courseRequestDto);
    }

    @Override
    public StaffResponseDto addLecture(StaffRequestDto staffRequestDto) {
        return lecturerService.addLecture(staffRequestDto);
    }

    @Override
    public SubjectResponseDto addSubject(SubjectRequestDto subject) {
        return subjectService.addSubject(subject);
    }

    @Override
    public StaffResponseDto assignSubject(long staffNo, List<Long> subjectId) {
        return subjectService.assignSubject(staffNo , subjectId);
    }

    @Override
    public Response createTimeTable(CreateTimeTableRequest request) {
        Optional<Staff> staff = userRepository.findByStaffNo(request.getStaff());

        if (staff.isPresent()){

            List<DayTimeSubjectRequest> timeSlots = request.getSubjectsSlots();
            List<Timetable>  timetableList = new ArrayList<>();
            for ( DayTimeSubjectRequest slots :timeSlots){
                Timetable timetable = new Timetable();
                Optional<Subject> subject = subjectRepository.findById(slots.getSubject());
                if(subject.isPresent()){
                    StaffSubject staffSubject = staffSubjectRepository.findByStaffAndSubjectId(staff.get(),subject.get());
                    if (staffSubject!=null){
                        timetable.setPeriods(slots.getPeriod());
                        timetable.setWeekDays(slots.getWeekDays());
                        timetable.setStaffSubject(staffSubject);
                        timetableList.add(timetable);
                    }
                }

            }
            timetableRepository.saveAll(timetableList);
            return new Response("Success","200");
        }
        else
        {
            throw new RuntimeException("Staff Does not exist");
        }

    }

    public List<SubjectResponseDto> allSubjects() {
        return subjectService.allSubjects();
    }

    public List<StaffResponseDto> alllectures() {
        return lecturerService.allLecturers();
    }

    public Response scheduleClass(long staff , long subject , WeekDays weekDays, Periods period ){

       return  timeTable.createTimetable(staff , subject ,weekDays , period );
    }


    public StaffResponseDto lectureById(long id) {
        return lecturerService.getLectureByStaffNo(id);
    }




}
