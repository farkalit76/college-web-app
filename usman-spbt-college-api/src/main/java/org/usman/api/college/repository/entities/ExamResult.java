package org.usman.api.college.repository.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "exam_results")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamResult extends AuditFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Long resultId;

//    @ManyToOne
//    @JoinColumn(name = "exam_id")
//    private Examination exam;
    @Column(name = "exam_id")
    private Integer examId;

//    @ManyToOne
//    @JoinColumn(name = "student_id")
//    private Student student;
    @Column(name = "student_id")
    private String studentId;

//    @ManyToOne
//    @JoinColumn(name = "subject_id")
//    private Subject subject;
    @Column(name = "subject_id")
    private Integer subjectId;

    @Column(name = "marks_obtained", precision = 5, scale = 2)
    private BigDecimal marksObtained;

    @Column(name = "grade")
    private String grade;
}
