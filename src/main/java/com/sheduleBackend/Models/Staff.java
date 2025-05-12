package com.sheduleBackend.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.sheduleBackend.Enums.Role;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "staff"
,  uniqueConstraints = @UniqueConstraint(columnNames ="{staff_no}")
)
public class Staff extends User{

    @Column(name = "staff_no")
    private long staffNo;

    @Column(name = "user_role")
    private Role userRole;

    @OneToMany(mappedBy = "staff")
    List<StaffSubject>  staffSubjects;
}
