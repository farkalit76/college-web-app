package org.usman.api.college.repository.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "semesters")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Semester extends AuditFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "semester_id")
    private Long semesterId;

//    @ManyToOne
//    @JoinColumn(name = "course_code", referencedColumnName = "courseCode")
//    private Course course;
    @Column(name = "course_code")
    private String courseCode;

    @Column(name = "semester_no")
    private int semesterNo;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;
}
