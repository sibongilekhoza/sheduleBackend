package com.sheduleBackend.Api.Response;

import com.sheduleBackend.Enums.Role;
import com.sheduleBackend.Models.StaffSubject;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class LectureReponse {
    private long userId;
    private String email;
    private String name;
    private String surname;
    private String phone;
    private long staffNo;
    private Role userRole;
    List<StaffSubject> staffSubjects;
}
