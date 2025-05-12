package com.sheduleBackend.Models;


import com.sheduleBackend.Api.Requests.StudentRequest;
import com.sheduleBackend.Utils.NumberGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name="student",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"stud_no"})
)

public class Student extends User{
    @Column(name = "stud_no")
    private long studentNo;

    public Student populateStudent(StudentRequest studentRequest){
        this.setEmail(studentRequest.getEmail());
        this.setName(studentRequest.getName());
        this.setSurname(studentRequest.getSurname());
        this.setStudentNo(NumberGenerator.generateNumber());
        this.setPhone(studentRequest.getPhone());
        return this;
    }

}
