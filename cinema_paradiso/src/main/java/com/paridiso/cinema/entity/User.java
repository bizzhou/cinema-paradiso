package com.paridiso.cinema.entity;

import com.paridiso.cinema.entity.enumerations.Role;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Users", uniqueConstraints = @UniqueConstraint(columnNames = {"username", "email"}))
public class User {

    private Integer userId;

    private String username;

    private String email;

    private String password;

    private Role role;

    private Boolean isAccountSuspended;

    private UserProfile userProfile;

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getUserID() {
        return userId;
    }

    @Size(max = 100)
    public String getUsername() {
        return username;
    }

    @Email
    @Size(max = 100)
    public String getEmail() {
        return email;
    }

    @Size(min = 8)
    public String getPassword() {
        return password;
    }

    @Column(columnDefinition = "enum('ROLE_ADMIN','ROLE_CRITIC','ROLE_USER')")
    @Enumerated(EnumType.STRING)
    public Role getRole() {
        return role;
    }

    public Boolean getAccountSuspended() {
        return isAccountSuspended;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "userProfileId", nullable = false)
    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserID(Integer userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setAccountSuspended(Boolean accountSuspended) {
        isAccountSuspended = accountSuspended;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
