package com.sheduleBackend.Api.Response;

import com.sheduleBackend.Models.Subject;
import lombok.Data;

import java.util.List;

@Data
public class CourseDetails extends CourseResponse {

    private List<Subject> subjectList;
}
