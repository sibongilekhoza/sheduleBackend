package com.sheduleBackend.BusinessServices.Interface;

import com.sheduleBackend.Api.Response.TimeSlotsResponse;
import com.sheduleBackend.Models.Dto.responses.StaffResponseDto;
import org.springframework.stereotype.Service;
import com.sheduleBackend.Models.Staff;
import com.sheduleBackend.Models.Dto.requests.StaffRequestDto;

import java.util.List;


public interface LecturerService {
    StaffResponseDto addLecture (StaffRequestDto staffRequestDto);

    List<StaffResponseDto> allLecturers();

    StaffResponseDto getLectureByStaffNo(long staffNo);

    void deleteLecturer(long id);

    List<TimeSlotsResponse> getTimeTable(Long id);
}
