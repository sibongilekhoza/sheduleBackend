package com.sheduleBackend.BusinessServices.Interface;

import com.sheduleBackend.Api.Requests.StudentRequest;
import com.sheduleBackend.Api.Response.Response;
import com.sheduleBackend.Api.Response.StudentDetails;
import com.sheduleBackend.Api.Response.StudentResponse;

import java.util.List;


public interface StudentService {
    Response AddStudent(StudentRequest studentRequest);

    List<StudentResponse> getAllStudents();
    StudentResponse getById(long id);

    List<StudentResponse> studentPerSubject(long subjectid);

//    StudentDetails getById(long id);
}
