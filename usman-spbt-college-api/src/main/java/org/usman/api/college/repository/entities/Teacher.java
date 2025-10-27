package org.usman.api.college.repository.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "teachers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Builder
public class Teacher extends AuditFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacherid")
    private Long teacherId;

    @Column(name = "firstname", nullable = false, length = 20)
    private String firstName;

    @Column(name = "lastname", length = 20)
    private String lastName;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false, length = 1)
    private String gender;

    @Column(nullable = false)
    private LocalDate hireDate;

    @Column(length = 255)
    private String address;

    @Column(length = 15)
    private String phoneNumber;

    @Column(length = 55)
    private String email;

    @Column(length = 50)
    private String qualification;

    private Integer experience;

    @Column(length = 100)
    private String subjectExpert;

    private Boolean isActive = true;

}
