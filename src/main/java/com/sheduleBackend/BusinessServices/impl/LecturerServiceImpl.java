package com.sheduleBackend.BusinessServices.impl;

import com.sheduleBackend.Api.Response.TimeSlotsResponse;
import com.sheduleBackend.Exceptions.SubjectException;
import com.sheduleBackend.Exceptions.UserException;
import com.sheduleBackend.Models.*;
import com.sheduleBackend.Models.Dto.requests.SubjectRequestDto;
import com.sheduleBackend.Models.Dto.responses.StaffResponseDto;
import com.sheduleBackend.Repositories.StaffSubjectRepository;
import com.sheduleBackend.Repositories.SubjectRepository;
import com.sheduleBackend.Repositories.TimetableRepository;
import com.sheduleBackend.Utils.NumberGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sheduleBackend.Models.Dto.requests.StaffRequestDto;
import com.sheduleBackend.Enums.Role;
import com.sheduleBackend.BusinessServices.Interface.LecturerService;
import com.sheduleBackend.Repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class LecturerServiceImpl implements LecturerService {

    @Autowired
    private UserRepository lecturerRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private StaffSubjectRepository staffSubjectRepository;
    @Autowired
    private ModelMapper staffDtoMapper;

    @Autowired
    private TimetableRepository timetableRepository;

    @Transactional
    @Override
    public StaffResponseDto addLecture(StaffRequestDto staffRequestDto) {
        Optional<Staff> existsStaffNo = lecturerRepository
                .findByStaffNo(staffRequestDto.getStaffNo());
        User existsEmail = lecturerRepository
                .findByEmail(staffRequestDto.getEmail());

        if (!lecturerRepository.existsByEmail(staffRequestDto.getEmail()) ) {
            staffRequestDto.setStaffNo(NumberGenerator.generateNumber());
            Staff mappedRequest = staffDtoMapper.map(staffRequestDto , Staff.class);
            mappedRequest.setUserRole(Role.Lecturer);
            Staff lecturer = mappedRequest;
            List<SubjectRequestDto> subjects = staffRequestDto.getSubjects();
            lecturerRepository.save(mappedRequest);
            for (SubjectRequestDto subjectRequests : subjects){

                Optional<Subject> existSubject = subjectRepository.findById(subjectRequests.getId());

                if (existSubject.isPresent()){
                    StaffSubject staffSubject = new StaffSubject();
                    staffSubject.setSubjectId(existSubject.get());
                    staffSubject.setStaff(lecturer);
                    System.out.println(staffSubject.toString());
                    staffSubjectRepository.save(staffSubject);
                }
                else {
                    throw new SubjectException("Subjects do not exist");
                }
                }

            StaffResponseDto staffResponse =  staffDtoMapper.map(mappedRequest, StaffResponseDto.class);
            return staffResponse;
        }
        throw new IllegalStateException("Lecturer with Staff-No:"+staffRequestDto.getStaffNo()
                +" /email : " +staffRequestDto.getEmail() + " already exists");
    }
    @Override
    public List<StaffResponseDto> allLecturers() {

        List<Staff> allByUserRole = lecturerRepository.findAllByUserRole(Role.Lecturer);

        List<StaffResponseDto> staffResponseDtos = allByUserRole.stream()
                .map(lecturers -> staffDtoMapper.map(lecturers, StaffResponseDto.class))
                .toList();

        return staffResponseDtos;
    }


    @Override
    public StaffResponseDto getLectureByStaffNo(long staffNo) {
        Optional<Staff> getById = lecturerRepository.findByStaffNo(staffNo);
        if (getById.isPresent() && getById.get()
                .getUserRole().equals(Role.Lecturer)) {
                 Staff staff = getById.get();
                return staffDtoMapper.map(staff, StaffResponseDto.class);
        }
        else {
            throw new RuntimeException("Not found");

        }
    }

    @Override
    public void deleteLecturer(long id) {
        Optional<Staff> existingLecturer = lecturerRepository.findByStaffNo(id);

        if (existingLecturer.isPresent()) {
            staffSubjectRepository.deleteById(existingLecturer.get().getStaffNo());
            staffSubjectRepository.deleteAll(existingLecturer.get().getStaffSubjects());
            lecturerRepository.delete(existingLecturer.get());


        } else {
            throw new IllegalStateException("Lecturer with Staff-No: " + id + " does not exist");
        }
    }

    @Override
    public List<TimeSlotsResponse> getTimeTable(Long id) {
        Optional<Staff> staff = lecturerRepository.findByStaffNo(id);
        if (staff.isPresent()){
            List<StaffSubject> staffSubjects = staffSubjectRepository.findAllByStaff(staff.get());
            List<TimeSlotsResponse> timeTables = new ArrayList<>();
            List<Timetable> timetableList= timetableRepository.findALLByStaffSubjectIn(staffSubjects);
            for (Timetable timetable:timetableList){
                TimeSlotsResponse slotsResponse= new TimeSlotsResponse();
                slotsResponse.setSubject(timetable.getStaffSubject().getSubjectId().getSubjectName());
                slotsResponse.setWeekDays(timetable.getWeekDays().ordinal());
                slotsResponse.setPeriod(timetable.getPeriods().ordinal());
                timeTables.add(slotsResponse);
            }
            return  timeTables;
        }else {
            throw new UserException("Can not find lecture Details");
        }

    }
}
