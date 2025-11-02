package com.usman.keycloak.model;

import jakarta.persistence.*;

@NamedQueries({
        @NamedQuery(name="getUserByUsername", query="select u from MyUserEntity u where u.username = :username"),
        @NamedQuery(name="getUserByEmail", query="select u from MyUserEntity u where u.email = :email"),
        @NamedQuery(name="getUserCount", query="select count(u) from MyUserEntity u"),
        @NamedQuery(name="getAllUsers", query="select u from MyUserEntity u"),
        @NamedQuery(name="searchForUser", query="select u from MyUserEntity u where " +
                "( lower(u.username) like :search or u.email like :search ) order by u.username"),
})
@Entity
@Table(name = "myuserentity")
public class MyUserEntity {

    @Id
    private String id;

    private String username;

    private String password;

    private String email;

    private String phone;

    private String firstName;

    private String lastName;


//    private Integer role; // ADMIN=1, MANAGER, USER etc.
//
//    private Integer status; //ACTIVE=1, INACTIVE, SUSPENDED, DELETED




//    @Column(name = "enabled")
//    private boolean enabled = true;
//
//    @Column(name = "lastloginat")
//    private Timestamp lastLoginAt; //Last successful login time
//
//    @Column(name = "loginattempts")
//    private Integer loginAttempts; //For lockout policies
//
//    @Column(name = "createdat")
//    private LocalDateTime createdAt;
//
//    @Column(name = "createdby")
//    private String createdBy;
//
//    @Column(name = "updatedat")
//    private LocalDateTime updatedAt;
//
//    @Column(name = "updatedby")
//    private String updatedBy;

    public MyUserEntity(){}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "MyUserEntity{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

//    public Integer getRole() {
//        return role;
//    }
//
//    public void setRole(Integer role) {
//        this.role = role;
//    }
//
//    public Integer getStatus() {
//        return status;
//    }
//
//    public void setStatus(Integer status) {
//        this.status = status;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public boolean isEnabled() {
//        return enabled;
//    }
//
//    public void setEnabled(boolean enabled) {
//        this.enabled = enabled;
//    }
//
//    public Timestamp getLastLoginAt() {
//        return lastLoginAt;
//    }
//
//    public void setLastLoginAt(Timestamp lastLoginAt) {
//        this.lastLoginAt = lastLoginAt;
//    }
//
//    public Integer getLoginAttempts() {
//        return loginAttempts;
//    }
//
//    public void setLoginAttempts(Integer loginAttempts) {
//        this.loginAttempts = loginAttempts;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public String getCreatedBy() {
//        return createdBy;
//    }
//
//    public void setCreatedBy(String createdBy) {
//        this.createdBy = createdBy;
//    }
//
//    public LocalDateTime getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(LocalDateTime updatedAt) {
//        this.updatedAt = updatedAt;
//    }
//
//    public String getUpdatedBy() {
//        return updatedBy;
//    }
//
//    public void setUpdatedBy(String updatedBy) {
//        this.updatedBy = updatedBy;
//    }
//
//    @Override
//    public String toString() {
//        return "MyUser{" +
//                "userId=" + userId +
//                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", role=" + role +
//                ", status=" + status +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", email='" + email + '\'' +
//                ", enabled=" + enabled +
//                ", lastLoginAt=" + lastLoginAt +
//                ", loginAttempts=" + loginAttempts +
//                ", createdAt=" + createdAt +
//                ", createdBy='" + createdBy + '\'' +
//                ", updatedAt=" + updatedAt +
//                ", updatedBy='" + updatedBy + '\'' +
//                '}';
//    }
}
