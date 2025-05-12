package com.sheduleBackend.Api.Response;

import com.sheduleBackend.Enums.Periods;
import com.sheduleBackend.Enums.WeekDays;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TimeSlotsResponse {
    private String subject;
    private LocalDate date;
    private int period;
    private int weekDays;
}
