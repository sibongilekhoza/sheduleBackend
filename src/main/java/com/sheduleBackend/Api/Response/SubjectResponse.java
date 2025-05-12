package com.sheduleBackend.Api.Response;

import lombok.Data;

@Data
public class SubjectResponse {
    private String subjectCode;
    private String subjectName;
    private  String course;
    private long attendance;
}
