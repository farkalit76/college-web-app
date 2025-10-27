package org.usman.api.college.repository.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "subjects")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subject extends AuditFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long subjectId;

//    @ManyToOne
//    @JoinColumn(name = "course_code", referencedColumnName = "course_code")
//    private Course course;
    @Column(name = "course_code")
    private String courseCode;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "subject_code", unique = true)
    private String subjectCode;
}
