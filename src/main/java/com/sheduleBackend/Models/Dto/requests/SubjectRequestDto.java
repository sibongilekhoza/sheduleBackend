package com.sheduleBackend.Models.Dto.requests;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectRequestDto {
    private long id;
    private String subjectLevel;
    private String subjectName;
    private String subjectCode;
    private long courseId;
}
