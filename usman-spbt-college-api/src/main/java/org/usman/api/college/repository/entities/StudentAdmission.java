package org.usman.api.college.repository.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "student_admissions")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentAdmission extends AuditFields{

    @Id
    @Column(name = "admission_id", length = 10)
    private String admissionId;

//    @ManyToOne
//    @JoinColumn(name = "student_id")
//    private Student student;
    @Column(name = "student_id")
    private String studentId;

//    @ManyToOne
//    @JoinColumn(name = "course_code", referencedColumnName = "course_code")
//    private Course course;
    @Column(name = "course_code")
    private String courseCode;

    @Column(name = "admission_year")
    private int admissionYear;

    @Column(name = "status")
    private String status;
}
