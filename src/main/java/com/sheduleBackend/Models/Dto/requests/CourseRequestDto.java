package com.sheduleBackend.Models.Dto.requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequestDto {

    private String courseLevel;
    private String courseCode;
    private String courseName;
    private String courseDescription;
}
