package com.sheduleBackend.Models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table
@Data
public class Attendance {
    @Id
    @GeneratedValue
    private  Long Id;
    @ManyToOne
    @JoinColumn(name="student_subject")
    private RegistrationSubject registrationSubject;
    private Date date;
    private String record;

    public  Attendance populateAttendance(RegistrationSubject reg ,String record){
        this.setDate(new Date());
        this.setRegistrationSubject(reg);
        this.setRecord(record);

        return this;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public RegistrationSubject getRegistrationSubject() {
        return registrationSubject;
    }

    public void setRegistrationSubject(RegistrationSubject registrationSubject) {
        this.registrationSubject = registrationSubject;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }
}
