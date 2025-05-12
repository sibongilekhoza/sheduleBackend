package com.sheduleBackend.Models.Dto;

import com.sheduleBackend.Models.Dto.requests.StaffRequestDto;
import com.sheduleBackend.Models.Dto.responses.StaffResponseDto;
import com.sheduleBackend.Models.Staff;

public class StaffDtoMapper {

    public StaffResponseDto mapToDto(Staff staff){

        StaffResponseDto staffResponseDto = new StaffResponseDto();
        staffResponseDto.setId(staff.getUserId());
        staffResponseDto.setStaffNo(staff.getStaffNo());
        staffResponseDto.setName(staff.getName());
        staffResponseDto.setEmail(staff.getEmail());
        staffResponseDto.setPhone(staffResponseDto.getPhone());
        staffResponseDto.setRole(staff.getUserRole());

        return staffResponseDto;
    }

    public Staff mapToEntity(StaffRequestDto staffRequestDto){

        Staff staff = new Staff();

        staff.setStaffNo(staffRequestDto.getStaffNo());
        staff.setName(staffRequestDto.getName());
        staff.setEmail(staffRequestDto.getEmail());
        staff.setPhone(staffRequestDto.getPhone());


        return staff;
    }

}
