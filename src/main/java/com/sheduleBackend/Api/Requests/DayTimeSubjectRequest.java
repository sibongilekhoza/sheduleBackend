package com.sheduleBackend.Api.Requests;


import com.sheduleBackend.Enums.Periods;
import com.sheduleBackend.Enums.WeekDays;
import lombok.Data;

import java.time.LocalDate;

@Data
public class DayTimeSubjectRequest {
    private long subject;
    private LocalDate date;
    private Periods period;
    private WeekDays weekDays;
}
