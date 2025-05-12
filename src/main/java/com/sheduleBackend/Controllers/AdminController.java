package com.sheduleBackend.Controllers;

import com.sheduleBackend.Api.Requests.*;
import com.sheduleBackend.Api.Response.*;
import com.sheduleBackend.Api.Response.*;
import com.sheduleBackend.BusinessServices.Interface.*;
import com.sheduleBackend.BusinessServices.impl.AdminServiceImpl;
import com.sheduleBackend.BusinessServices.impl.Authentication;
import com.sheduleBackend.BusinessServices.impl.SubjectServiceImpl;
import com.sheduleBackend.Models.*;
import com.sheduleBackend.Models.Dto.requests.SubjectRequestDto;
import com.sheduleBackend.Models.Dto.responses.StaffResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sheduleBackend.Models.Dto.requests.CourseRequestDto;
import com.sheduleBackend.Models.Dto.requests.StaffRequestDto;
import com.sheduleBackend.Models.Dto.responses.SubjectResponseDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin()
@RequestMapping("/admin")
public class AdminController {
    @Autowired
  private AdminService adminService;

    @Autowired
    private SubjectService subjectService;
     @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;
    @Autowired
    private Authentication authentication;
    @Autowired
   private  ModelMapper subjectModel;
    @Autowired
    private AdminServiceImpl adminServiceImpl;

    @Autowired
    private LecturerService lecturerService;

    @PostMapping("/add-lecturer")
    public ResponseEntity<StaffResponseDto> addNewLecturer(@RequestBody StaffRequestDto lecturerDetails){

        if (lecturerDetails != null){
            StaffResponseDto newLecturer = adminService.addLecture(lecturerDetails);
        return  ResponseEntity.ok(newLecturer);
        }
       return  ResponseEntity.notFound().build();
    }

    @PostMapping("/add-course")
    public Course addNewCourse(@RequestBody CourseRequestDto courseRequestDto) {
        Course newCourse = adminService.createCourse(courseRequestDto);
        return newCourse;
    }


    @PostMapping("/add-module")
    public SubjectResponseDto addNewModule(@RequestBody SubjectRequestDto subject) {

        return adminService.addSubject(subject);
    }
    @PatchMapping("/assign-module/{staffNo}")
    public StaffResponseDto addNewModule(@PathVariable long staffNo , @RequestBody List<Long> subjects) {
        return adminService.assignSubject(staffNo ,subjects);
    }


    @PostMapping("/addStudent")
    public ResponseEntity<Response> addNewStudent(@RequestBody StudentRequest studentRequest){
        Response response= studentService.AddStudent(studentRequest);
        return  ResponseEntity.ok(response);
    }



    @GetMapping("/all-lecturers")
    public List<StaffResponseDto> allLectures(){

        List<StaffResponseDto> all = adminServiceImpl.alllectures();
        return all;
    }


    @GetMapping("/all-subjects")
    public List<SubjectResponseDto> allSubjects(){

        List<SubjectResponseDto> all = adminServiceImpl.allSubjects();
        return all;
    }
    @PostMapping("/schedule")
    public Response addNewStudent(@RequestBody TimetableRequest timetableRequest){

        System.out.println(timetableRequest.getStaff() + timetableRequest.getSubject());

        return adminServiceImpl.scheduleClass( timetableRequest.getStaff(),
                timetableRequest.getSubject() ,  timetableRequest.getWeekDays(),  timetableRequest.getPeriod());
    }

    @GetMapping("/all-user-subjects/{staffNo}")
    public List<SubjectResponseDto> userSubjects(@PathVariable  long staffNo) {

        List<SubjectResponseDto> all = subjectService.allUserSubjects(staffNo);

        return all;
    }
    @GetMapping("/courses")
    public ResponseEntity<List<CourseResponse>> getAllCourses(){
        List<CourseResponse> list = courseService.getAllCourse();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseDetails> getCourseBeforeId(@PathVariable Long id) {
        CourseDetails courseBeforeId = courseService.getCourseById(id);
        if (courseBeforeId != null) {
            return new ResponseEntity<>(courseBeforeId, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/subject/{id}")
    public  ResponseEntity<List<SubjectResponseDto>> getSubjectByCourse(@PathVariable Long id){
        List<SubjectResponseDto> list = subjectService.getByCourse(id);
        return  new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/subject")
    public  ResponseEntity<List<SubjectResponseDto>> getAllSubject(){
        List<SubjectResponseDto> list = subjectService.getAllSubject();
        return  new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/students")
    public  ResponseEntity<List<StudentResponse>> getAllStudents(){
        List<StudentResponse> list = studentService.getAllStudents();
        return  new ResponseEntity<>(list,HttpStatus.OK);
    }

    @GetMapping("/lecturer/{id}")
    public StaffResponseDto lecturer(@PathVariable  long id) {
        return adminServiceImpl.lectureById(id) ;
    }
    @GetMapping("/student/{id}")
    public StudentResponse getStudent(@PathVariable  long id) {

        return studentService.getById(id) ;
    }
    @PostMapping("/log-in")
    public StaffResponseDto logIn(@RequestBody LogIn logIn){
        return authentication.logIn(logIn.getEmail() ,logIn.getPassword());
    }
    @GetMapping("/student-subjects/{id}")
    public List<StudentResponse> logIn(@PathVariable long id){
        return studentService.studentPerSubject(id);
    }
    @PutMapping("/update-course/{id}")
    public Course updateCourse(@PathVariable long id, @RequestBody CourseRequestDto courseRequestDto){
        return courseService.updateCourse(id, courseRequestDto);
    }


    @PostMapping("/createTimetable")
    public  Response createTimeTable(@RequestBody CreateTimeTableRequest request){

        return  adminService.createTimeTable(request);
    }
    @DeleteMapping("/courseDelete/{id}")
    public void deleteCourse(@PathVariable Long id) {

        courseService.deleteCourse(id);
    }

    @DeleteMapping("/subjectDelete/{id}")
    public void deleteSubject(@PathVariable Long id) {

        subjectService.delete(id);
    }

    @DeleteMapping("/lectureDelete/{id}")
    public void deleteLecture(@PathVariable Long id) {
       lecturerService.deleteLecturer(id) ;

    }

    @GetMapping("/subjects/{id}")
    public  ResponseEntity<SubjectResponse> getSubjectById(@PathVariable Long id){
        SubjectResponse subject = subjectService.geSubjectById(id);
        return  new ResponseEntity<>(subject,HttpStatus.OK);
    }



}
