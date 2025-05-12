package com.sheduleBackend.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Entity
@Table
@Data
public class RegistrationSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;

    private Date subjectRegistrationDate;
    private  String status;
    @ManyToOne(fetch = FetchType.LAZY)
    private Registrations registrations;
    @ManyToOne(fetch = FetchType.LAZY)
    private  Subject subject;


    public RegistrationSubject populateRegistration(Registrations registration,Subject subject){
        this.setSubject(subject);
        this.setRegistrations(registration);
        this.setSubjectRegistrationDate(new Date());
        this.setStatus("Active");

        return this;
    }
}
