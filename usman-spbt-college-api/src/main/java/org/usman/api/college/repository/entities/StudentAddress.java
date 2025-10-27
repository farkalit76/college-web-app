package org.usman.api.college.repository.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "student_address")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentAddress extends AuditFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

//    @ManyToOne
//    @JoinColumn(name = "student_id", nullable = false)
//    private Student student;
    @Column(name = "student_id")
    private String studentId;

    @Column(name = "address_line1")
    private String addressLine1;

    @Column(name = "address_line2")
    private String addressLine2;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "country")
    private String country;

}
