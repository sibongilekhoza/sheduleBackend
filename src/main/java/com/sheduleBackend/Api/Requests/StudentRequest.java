package com.sheduleBackend.Api.Requests;


import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class StudentRequest {
    private String email;
    private String name;
    private String surname;
    private String phone ;
    private String password;
    private long coursedId;
    private List<Long> subjectId;
}
