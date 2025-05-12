package com.sheduleBackend.BusinessServices.Interface;

import com.sheduleBackend.Api.Requests.RegisterRequest;
import com.sheduleBackend.Api.Response.Response;
import com.sheduleBackend.Api.Response.SubjectResponse;
import com.sheduleBackend.Models.Dto.requests.SubjectRequestDto;
import com.sheduleBackend.Models.Dto.responses.StaffResponseDto;
import com.sheduleBackend.Models.Dto.responses.SubjectResponseDto;
import com.sheduleBackend.Models.StaffSubject;
import com.sheduleBackend.Models.Subject;
import com.sheduleBackend.Models.User;

import java.util.List;


public interface SubjectService {
    SubjectResponseDto addSubject(SubjectRequestDto subject);
    StaffResponseDto assignSubject(long staffNo , List<Long> subjectId);

    List<SubjectResponseDto> getByCourse(Long id);

    List<SubjectResponseDto> getAllSubject();
    List<SubjectResponseDto> allSubjects();

    List<SubjectResponseDto> allUserSubjects(long staffno);

    Response markAttendance(RegisterRequest registerRequest);

    void delete(Long id);

    SubjectResponse geSubjectById(Long id);

}
