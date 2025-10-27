package org.usman.api.college.repository.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "fee_payments")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeePayment extends AuditFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

//    @ManyToOne
//    @JoinColumn(name = "admission_id")
//    private StudentAdmission admission;
    @Column(name = "admission_id")
    private String admissionId;

    @Column(name = "installment_no")
    private int installmentNo;

    @Column(name = "amount_paid")
    private BigDecimal amountPaid;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column(name = "payment_mode")
    private String paymentMode;

}
