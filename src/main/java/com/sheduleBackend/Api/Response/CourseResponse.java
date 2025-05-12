package com.sheduleBackend.Api.Response;


import lombok.Data;

@Data
public class CourseResponse {
    private long courseId;
    private String courseCode;
    private String courseName;
    private String courseLevel;
    private String courseDescription;
}
