package com.sheduleBackend.BusinessServices.Interface;

import com.sheduleBackend.Api.Response.CourseDetails;
import com.sheduleBackend.Api.Response.CourseResponse;
import org.springframework.stereotype.Service;
import com.sheduleBackend.Models.Course;
import com.sheduleBackend.Models.Dto.requests.CourseRequestDto;

import java.util.List;


public interface CourseService {
    Course createCourse(CourseRequestDto courseRequestDto);
    void deleteCourse(long courseId);
    List<CourseResponse> getAllCourse();

    Course updateCourse(long courseId, CourseRequestDto courseRequestDto);

    CourseDetails getCourseById(Long id);

}
