package com.paridiso.cinema.entity;

import com.paridiso.cinema.entity.enumerations.Role;
import com.paridiso.cinema.service.WishlistService;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Users", uniqueConstraints = @UniqueConstraint(columnNames = {"username", "email"}))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Size(max = 100)
    @Column(name = "username")
    private String username;

    @Email
    @Size(max = 100)
    @Column(name = "email")
    private String email;

    @Size(min = 8)
    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "suspended")
    private Boolean isAccountSuspended;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserProfile userProfile;

    public User() {
    }

    public void setAccountSuspended(Boolean accountSuspended) {
        isAccountSuspended = accountSuspended;
    }

    public Integer getUserID() {
        return userId;
    }

    public void setUserID(Integer userID) {
        this.userId = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Boolean getAccountSuspended() {
        return isAccountSuspended;
    }

    public UserProfile getUserProfile() {
        return (userProfile != null ? userProfile : new UserProfile());
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", isAccountSuspended=" + isAccountSuspended +
                ", userProfile=" + userProfile +
                '}';
    }
}
