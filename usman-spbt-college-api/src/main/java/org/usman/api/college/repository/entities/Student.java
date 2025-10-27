package org.usman.api.college.repository.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "students")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student extends AuditFields{

    @Id
    @Column(name = "student_id", length = 10)
    private String studentId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "gender")
    private String gender;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name="phone", unique = true)
    private String phone;

    @Column(name = "admission_date")
    private LocalDate admissionDate;

    @Column(name = "isactive")
    private boolean isActive;
}