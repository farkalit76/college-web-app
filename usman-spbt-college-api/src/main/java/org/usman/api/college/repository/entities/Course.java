package org.usman.api.college.repository.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "courses")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course extends AuditFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_code", unique = true)
    private String courseCode;

    @Column(name = "duration_months")
    private int durationMonths;

}
