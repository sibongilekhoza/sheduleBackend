package com.sheduleBackend.Models.Dto.requests;

import com.sheduleBackend.Models.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffRequestDto {

    private String email;
    private String name;
    private String surname;
    private String phone ;
    private String password;
    private long staffNo;
    private List<SubjectRequestDto> subjects;


}
