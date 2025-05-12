package com.sheduleBackend.BusinessServices.Interface;

import com.sheduleBackend.Api.Requests.CreateTimeTableRequest;
import com.sheduleBackend.Api.Response.Response;
import com.sheduleBackend.Models.Course;
import com.sheduleBackend.Models.Dto.requests.CourseRequestDto;
import com.sheduleBackend.Models.Dto.requests.StaffRequestDto;
import com.sheduleBackend.Models.Dto.requests.SubjectRequestDto;
import com.sheduleBackend.Models.Dto.responses.StaffResponseDto;
import com.sheduleBackend.Models.Dto.responses.SubjectResponseDto;
import com.sheduleBackend.Models.Enrollment;
import com.sheduleBackend.Models.Staff;
import com.sheduleBackend.Models.Subject;

import java.util.List;


public interface AdminService {
    Course createCourse(CourseRequestDto courseRequestDto);
    StaffResponseDto addLecture (StaffRequestDto staffRequestDto);
    SubjectResponseDto addSubject(SubjectRequestDto subject);
    StaffResponseDto assignSubject(long staffNo , List<Long> subjectId);


    Response createTimeTable(CreateTimeTableRequest request);

}
