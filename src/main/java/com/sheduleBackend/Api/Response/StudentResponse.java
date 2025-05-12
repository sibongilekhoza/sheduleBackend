package com.sheduleBackend.Api.Response;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class StudentResponse {

    private long studentNo;
    private long userId;
    private String email;
    private String name;
    private String surname;
    private String phone ;
    private String password;
    private String course;
    private Date  enrolDate;
    private String courseCode;
    private String courseLevel;
    private List<SubjectResponse> subjectList;
    private long subjectRegId;


}
