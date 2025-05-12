package com.sheduleBackend.Api.Requests;

import com.sheduleBackend.Enums.Periods;
import com.sheduleBackend.Enums.WeekDays;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class TimetableRequest {

        private long staff;
        private long subject;
        private LocalDate date;
        private Periods period;
        private WeekDays WeekDays;


    }

