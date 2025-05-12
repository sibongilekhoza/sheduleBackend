package com.sheduleBackend.Models.Dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.sheduleBackend.Enums.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffResponseDto {


    private long id;
    private String email;
    private String name;
    private String surname;
    private String phone ;
    private Role role;
    private long staffNo;

}
