package com.sheduleBackend.BusinessServices.impl;

import com.sheduleBackend.Api.Requests.RegisterRequest;
import com.sheduleBackend.Api.Response.Response;
import com.sheduleBackend.Api.Response.SubjectResponse;
import com.sheduleBackend.Enums.Role;
import com.sheduleBackend.Exceptions.CourseException;
import com.sheduleBackend.Models.*;
import com.sheduleBackend.Models.Dto.requests.SubjectRequestDto;
import com.sheduleBackend.Models.Dto.responses.StaffResponseDto;
import com.sheduleBackend.Repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sheduleBackend.BusinessServices.Interface.SubjectService;
import com.sheduleBackend.Exceptions.SubjectException;
import com.sheduleBackend.Models.Dto.responses.SubjectResponseDto;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private ModelMapper subjectMapper;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StaffSubjectRepository staffSubRepo;

    @Autowired
    private RegistrationSubjectRepository registrationSubjectRepository;

    @Autowired
    private  AttendanceRepository attendanceRepository;

    @Override
    public SubjectResponseDto addSubject(SubjectRequestDto subject) {

        Optional<Subject> existByName = subjectRepository.findBySubjectName(subject.getSubjectName());
        Optional<Subject> existByCode = subjectRepository.findBySubjectCode(subject.getSubjectCode());
        Optional<Course> courseExists = courseRepository.findById(subject.getCourseId());

        if (courseExists.isPresent() ) {
            if (existByName.isPresent() || existByCode.isPresent() ) {
                throw new SubjectException("Subject already exists");
            }
            else {
                Subject newSubject = new Subject();
                newSubject.setSubjectLevel(subject.getSubjectLevel());
                newSubject.setSubjectName(subject.getSubjectName());
                newSubject.setSubjectCode(subject.getSubjectCode());
                newSubject.setCourse(courseExists.get());
                Subject savedSubject = subjectRepository.save(newSubject);
                SubjectResponseDto mappedSubjectResponseDto = subjectMapper.map(savedSubject, SubjectResponseDto.class);
                return mappedSubjectResponseDto;
            }

        }
        throw new CourseException("Course doesn't exists");

    }
    @Override
    public StaffResponseDto assignSubject(long staffNo , List<Long> subjects) {
        Optional<Staff> staff = userRepository.findByStaffNo(staffNo);

        if (staff.isPresent()) {
           Staff  user = staff.get();
         boolean subExists = false;
        for (Long subjectId : subjects) {
                Optional<Subject> existingSubject = subjectRepository.findById(subjectId);
                if (existingSubject.isPresent()) {
                    Subject subject =  existingSubject.get();
                    subExists = true;
                    StaffSubject staffSubject = new StaffSubject();
                    staffSubject.setSubjectId(subject);
                    staffSubject.setStaff(user);
                    staffSubRepo.save(staffSubject);
                }
                else {
                    subExists = false;
                }
                if (!subExists){
                    throw new SubjectException("One or more subjects do nt exist");
                }
        }
        StaffResponseDto staffResponse = subjectMapper.map(user , StaffResponseDto.class);

            return staffResponse;
        }
        throw  new IllegalStateException("User does not exist");
    }

    @Override
    public List<SubjectResponseDto> getByCourse(Long id) {

        Course  course = courseRepository.findByCourseId(id);
        List<SubjectResponseDto> subjectResponseDtos = new ArrayList<>();
        if (course!=null){
            List<Subject> subjectList =subjectRepository.findByCourse(course);
            for (Subject subject : subjectList){
                SubjectResponseDto responseDto = new SubjectResponseDto();
                responseDto.setCourseId(subject.getCourse().getCourseId());
                responseDto.setSubjectName(subject.getSubjectName());
                responseDto.setSubjectCode(subject.getSubjectCode());
                responseDto.setSubjectLevel(subject.getSubjectLevel());
                responseDto.setId(subject.getSubjectId());
                subjectResponseDtos.add(responseDto);
            }
        }
        return subjectResponseDtos;
    }

    @Override
    public List<SubjectResponseDto> getAllSubject() {

        List<Subject> subjectList = subjectRepository.findAll();
        List<SubjectResponseDto> subjectResponseDtos = new ArrayList<>();
            for (Subject subject : subjectList){
                SubjectResponseDto responseDto = new SubjectResponseDto();
                responseDto.setCourseId(subject.getCourse().getCourseId());
                responseDto.setSubjectName(subject.getSubjectName());
                responseDto.setSubjectCode(subject.getSubjectCode());
                responseDto.setSubjectLevel(subject.getSubjectLevel());
                responseDto.setId(subject.getSubjectId());
                subjectResponseDtos.add(responseDto);
            }

        return subjectResponseDtos;
    }
    public List<SubjectResponseDto> allSubjects() {

        List<Subject> allSubjects = subjectRepository.findAll();

        List<SubjectResponseDto> subjectResponseDtos = allSubjects.stream()
                .map(subject -> subjectMapper.map(subject, SubjectResponseDto.class))
                .toList();


        return subjectResponseDtos;
    }

    @Override
    public List<SubjectResponseDto> allUserSubjects(long staffno) {
        Optional<Staff> lecturer = userRepository.findByStaffNo(staffno);
        List<Subject> allUserSubject = new ArrayList<>();
        if (lecturer.isPresent()) {
            Staff staff = lecturer.get();
            List<StaffSubject> subjectsOnStaff = staff.getStaffSubjects();


            for (StaffSubject all : subjectsOnStaff) {
                allUserSubject.add(all.getSubjectId());
            }

            List<SubjectResponseDto> allSub = allUserSubject.stream()
                    .map(subject -> subjectMapper.map(subject, SubjectResponseDto.class))
                    .toList();

            return allSub;
        }
      else {
          throw  new SubjectException("Not found");
        }
    }

    @Override
    public Response markAttendance(RegisterRequest registerRequest) {

        Subject subject = subjectRepository.findBySubjectId(registerRequest.getSubjectId());
        List<Long> resgSubjectList = registerRequest.getSubjects();
        List<RegistrationSubject> registrationSubjectList =registrationSubjectRepository.findAllBySubject(subject);
        if (subject!=null){
            List<Attendance> attendanceList = new ArrayList<>();
            for (RegistrationSubject regSub :registrationSubjectList){
                Attendance attendance =new Attendance();
                attendance.populateAttendance(regSub,"Present");
                for ( Long reg:resgSubjectList){
                    if (regSub.getId()==reg){
                       // attendance.populateAttendance(regSub,"Absent");
                        attendance.setRecord("Absent");
                        break;
                    }
                }
                attendanceList.add(attendance);
            }
            attendanceRepository.saveAll(attendanceList);


            return new Response("Attendance Marked","200");
        }
        else {
            throw  new SubjectException("Subject Not found");
        }

    }

    @Override
    public void delete(Long id) {
        subjectRepository.deleteById(id);
    }

    @Override
    public SubjectResponse geSubjectById(Long id) {
        Subject subject = subjectRepository.findBySubjectId(id);
        SubjectResponse response = new SubjectResponse();
        response.setSubjectCode(subject.getSubjectCode());
        response.setSubjectName(subject.getSubjectName());
        response.setCourse(subject.getCourse().getCourseName());
        return response;
    }


}
