package com.sheduleBackend.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Entity
@Table
@Data
public class Registrations {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long registrationId;

    private Date registrationDate;

    private  String registrationStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private  Enrollment enrollment;

    public  Registrations populateRegData(Enrollment enrollment){
        this.setRegistrationDate(new Date());
        this.setRegistrationStatus("Active");
        this.setEnrollment(enrollment);
        return this;
    }
}
