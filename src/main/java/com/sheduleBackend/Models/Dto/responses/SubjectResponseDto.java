package com.sheduleBackend.Models.Dto.responses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectResponseDto {
    private  long id;
    private long subjectId;
    private String subjectLevel;
    private String subjectName;
    private String subjectCode;
    private long courseId;
}
