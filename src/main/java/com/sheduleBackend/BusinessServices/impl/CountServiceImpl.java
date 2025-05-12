package com.sheduleBackend.BusinessServices.impl;


import com.sheduleBackend.BusinessServices.Interface.CountService;
import com.sheduleBackend.Repositories.CourseRepository;
import com.sheduleBackend.Repositories.StudentRepository;
import com.sheduleBackend.Repositories.SubjectRepository;
import com.sheduleBackend.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CountServiceImpl implements CountService {


    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public long subjectCount() {
        return subjectRepository.count();

    }

    @Override
    public long studentCount() {
        return studentRepository.count();
    }

    @Override
    public long courseCount() {
        return courseRepository.count();

    }

    @Override
    public long staffCount() {
        return userRepository.countStaff();
    }


    @Override
    public Map<String , Long> getCounts(){
        Map<String , Long> counts = new HashMap<>();
        counts.put("subjects",subjectCount());
        counts.put("students",studentCount());
        counts.put("courses",courseCount());
        counts.put("Lectures",staffCount());
    return counts;

    }



}
