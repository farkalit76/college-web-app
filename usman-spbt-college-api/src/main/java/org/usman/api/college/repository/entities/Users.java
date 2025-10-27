package org.usman.api.college.repository.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@EqualsAndHashCode(callSuper=false)
public class Users extends AuditFields{

    @Id
    @Column(name = "userId")
    private Long userId;

    @Column(name = "username")
    private String userName;

    private String password;

    private Integer role; // USER, ADMIN, MANAGER, etc.

    private Integer status; //ACTIVE, INACTIVE, SUSPENDED, DELETED

    @Column(name = "lastloginat")
    private Timestamp lastLoginAt; //Last successful login time

    @Column(name = "loginattempts")
    private Integer loginAttempts; //For lockout policies

}
