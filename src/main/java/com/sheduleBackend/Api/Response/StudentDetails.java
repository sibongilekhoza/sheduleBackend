package com.sheduleBackend.Api.Response;

import com.sheduleBackend.Models.Subject;
import lombok.Data;

import java.util.List;


@Data
public class StudentDetails {
    private String email;
    private String name;
    private long studentNo;
    private long subjectRegid;

}
