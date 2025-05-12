package com.sheduleBackend.Api.Requests;

import lombok.Data;

import java.util.List;

@Data
public class RegisterRequest {

    private  long subjectId ;
    private List<Long> subjects;
}
