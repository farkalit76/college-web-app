package org.usman.api.college.repository.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="audit_log")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auditId;

    @Column(name = "orderId")
    private long orderId;

    @Column(name = "action")
    private String action;

    @Column(name = "jsonData")
    private String jsonData;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name = "createdBy")
    private String createdBy;
}
