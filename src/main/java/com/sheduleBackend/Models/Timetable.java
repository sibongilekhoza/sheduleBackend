package com.sheduleBackend.Models;

import com.sheduleBackend.Enums.Periods;
import com.sheduleBackend.Enums.WeekDays;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Timetable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "staff_subject_id")
    private StaffSubject staffSubject;
    private Periods periods;
    private WeekDays weekDays;


}
