package com.sheduleBackend.Api.Requests;

import lombok.Data;

import java.util.List;

@Data
public class CreateTimeTableRequest {
    private long staff;
    private List<DayTimeSubjectRequest> subjectsSlots;
}
