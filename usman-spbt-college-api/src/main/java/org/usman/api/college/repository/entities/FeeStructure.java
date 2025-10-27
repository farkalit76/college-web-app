package org.usman.api.college.repository.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Entity
@Table(name = "fee_structure")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeeStructure extends AuditFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fee_id")
    private Long feeId;

//    @ManyToOne
//    @JoinColumn(name = "course_code", referencedColumnName = "courseCode")
//    private Course course;
    @Column(name = "course_code")
    private String courseCode;

    @Column(name = "total_fee")
    private BigDecimal totalFee;
}
