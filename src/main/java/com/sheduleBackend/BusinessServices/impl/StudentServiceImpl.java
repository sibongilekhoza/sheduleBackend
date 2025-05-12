package com.sheduleBackend.BusinessServices.impl;

import com.sheduleBackend.Api.Requests.StudentRequest;
import com.sheduleBackend.Api.Response.Response;
import com.sheduleBackend.Api.Response.StudentDetails;
import com.sheduleBackend.Api.Response.StudentResponse;
import com.sheduleBackend.Api.Response.SubjectResponse;
import com.sheduleBackend.BusinessServices.Interface.StudentService;
import com.sheduleBackend.Exceptions.CourseException;
import com.sheduleBackend.Exceptions.SubjectException;
import com.sheduleBackend.Exceptions.UserException;
import com.sheduleBackend.Models.*;
import com.sheduleBackend.Models.Dto.responses.StaffResponseDto;
import com.sheduleBackend.Repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private RegistrationSubjectRepository registrationSubjectRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private  AttendanceRepository attendanceRepository;

    @Autowired
    private  StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ModelMapper modelMapper;




    @Transactional
    @Override
    public Response AddStudent(StudentRequest studentRequest) {
        if (userRepository.existsByEmail(studentRequest.getEmail()))
        {
            throw new UserException("User already exists");
        }
        Student student = new Student();
        student.populateStudent(studentRequest);
        userRepository.save(student);
        if (studentRequest.getCoursedId()!=0){
            if (courseRepository.existsById(studentRequest.getCoursedId())){
                Course course = courseRepository.findByCourseId(studentRequest.getCoursedId());
                Enrollment enrollment = new Enrollment();
                enrollment.populateData(course,student);
                enrollmentRepository.save(enrollment);
                if (studentRequest.getSubjectId()!=null){
                    Registrations registrations = new Registrations();
                    registrations.populateRegData(enrollment);
                    List<Long> subjectListInput = studentRequest.getSubjectId();
                    List<RegistrationSubject> subjects = new ArrayList<>();
                    for (Long subId :subjectListInput){
                         Subject subject = subjectRepository.findBySubjectId(subId);
                         if (subject!=null){
                             RegistrationSubject registrationSubject = new RegistrationSubject();
                             registrationSubject.populateRegistration(registrations,subject);
                             subjects.add(registrationSubject);
                         }
                    }
                    registrationRepository.save(registrations);

                    registrationSubjectRepository.saveAll(subjects);
                }
            }
            else {
                throw  new CourseException("Course does not exits");
            }
        }

        return new Response("Student saved","200");
    }

    @Override
    public List<StudentResponse> getAllStudents() {

        StudentResponse studentResponse = new StudentResponse();

        List<Student> studentList = (List<Student>) studentRepository.findAll();

        return studentList.stream().map( stu->modelMapper.map(stu,StudentResponse.class))
                .collect(Collectors.toList());
    }

    //Ignore
    @Override
    public StudentResponse getById(long id){
        Optional<User> findStudentById = userRepository.findById(id);

        StudentResponse studentResponse= new StudentResponse();
       if (findStudentById.isEmpty()){
           throw new UserException("Not found");
       }
           User user =  findStudentById.get();
           if (user instanceof Student student){
               Optional<Enrollment> enrolledStudent = enrollmentRepository
                       .findByStudent(student);
               if (enrolledStudent.isPresent()){
                   Course course = enrolledStudent.get().getCourse();
                    studentResponse.setCourse(course.getCourseName());
                    studentResponse.setCourseCode(course.getCourseCode());
                    studentResponse.setCourseLevel(course.getCourseLevel());
                    studentResponse.setEnrolDate(enrolledStudent.get().getEnrolementDate());
                    Registrations registrations = registrationRepository.findByEnrollment(enrolledStudent.get());
                    List<RegistrationSubject> subjects = registrationSubjectRepository.findAllByRegistrations(registrations);
                   List<SubjectResponse> subjectResponseList = new ArrayList<>();
                    for ( RegistrationSubject subject :subjects){
                        SubjectResponse  response = new SubjectResponse();
                        response.setSubjectName(subject.getSubject().getSubjectName());
                        response.setSubjectCode(subject.getSubject().getSubjectCode());
                        Attendance attendance = new Attendance();
                        long reg = attendanceRepository.countByRegistrationSubject(subject);
                        long present =attendanceRepository.countByRegistrationSubjectAndRecord(subject,"Present");
                        if (reg!=0){
                            response.setAttendance(present/reg%100);
                        }
                        response.setAttendance(present);

                       subjectResponseList.add(response);
                    }
                    studentResponse.setSubjectList(subjectResponseList);

               System.out.println(enrolledStudent);

               }
              
               studentResponse.setStudentNo(((Student) user).getStudentNo());
               studentResponse.setPhone(user.getPhone());
               studentResponse.setName(user.getName());
               studentResponse.setSurname(user.getSurname());
               studentResponse.setUserId(user.getUserId());
               studentResponse.setEmail(user.getEmail());

               return studentResponse;

           }
           else {
               throw new UserException("User not a student");
           }
        }
  @Override
    public List<StudentResponse> studentPerSubject(long subjectid) {
      Subject subject = subjectRepository.findBySubjectId(subjectid);
      List<StudentResponse> users = new ArrayList<>();
      if (subject != null) {
          List<RegistrationSubject> allSubjects = registrationSubjectRepository.findAllBySubject(subject);
          for (RegistrationSubject subjects : allSubjects) {
         Registrations registrations = subjects.getRegistrations();
         Enrollment enrollment = registrations.getEnrollment();
               Student student =  enrollment.getStudent();
              System.out.println(student);
                  StudentResponse studentResponse = modelMapper.map(student ,
                          StudentResponse.class);
                  studentResponse.setSubjectRegId(subjects.getId());
                  users.add(studentResponse);
          }
          return users;
      }
   throw new SubjectException("Subject do not exist");
  }

    }
