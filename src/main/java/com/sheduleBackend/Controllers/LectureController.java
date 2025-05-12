package com.sheduleBackend.Controllers;


import com.sheduleBackend.Api.Requests.LogIn;
import com.sheduleBackend.Api.Requests.RegisterRequest;
import com.sheduleBackend.Api.Response.CourseDetails;
import com.sheduleBackend.Api.Response.Response;
import com.sheduleBackend.Api.Response.TimeSlotsResponse;
import com.sheduleBackend.BusinessServices.Interface.LecturerService;
import com.sheduleBackend.BusinessServices.Interface.SubjectService;
import com.sheduleBackend.BusinessServices.impl.Authentication;
import com.sheduleBackend.Models.Dto.responses.StaffResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/lecture")
public class LectureController {

    @Autowired
    private LecturerService lecturerService;
    @Autowired
    private SubjectService subjectService;

    @Autowired
    private Authentication authentication;

    @PostMapping("/log-in")
    public StaffResponseDto logIn(@RequestBody LogIn logIn){
        return authentication.logIn(logIn.getEmail() ,logIn.getPassword());
    }

    @GetMapping("/timeTable/{id}")
    public ResponseEntity<List<TimeSlotsResponse>> getTimeTable(@PathVariable Long id) {
        List<TimeSlotsResponse> timeSlotsResponses = lecturerService.getTimeTable( id);
        if (timeSlotsResponses != null) {
            return new ResponseEntity<>(timeSlotsResponses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @PostMapping("/markRegister")
    public Response SubjectAttendance(@RequestBody RegisterRequest registerRequest){

        return  subjectService.markAttendance(registerRequest);
    }
}
