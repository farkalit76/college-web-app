package org.usman.api.college.repository.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "examinations")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Examination extends AuditFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id")
    private Long examId;

//    @ManyToOne
//    @JoinColumn(name = "semester_id")
//    private Semester semester;
    @Column(name = "semester_id")
    private Integer semesterId;

    @Column(name = "exam_name")
    private String examName;

    @Column(name = "exam_date")
    private LocalDate examDate;
}
