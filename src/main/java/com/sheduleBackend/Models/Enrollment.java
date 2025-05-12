package com.sheduleBackend.Models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Entity
@Table
@Data
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long enrollmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    private Date enrolementDate;

    private  String status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;



    public  Enrollment populateData(Course course,Student student){
        this.setEnrolementDate(new Date());
        this.setCourse(course);
        this.setStudent(student);
        this.setStatus("Active");
        return this;
    }
}
