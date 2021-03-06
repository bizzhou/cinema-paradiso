package com.paridiso.cinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.paridiso.cinema.entity.enumerations.Role;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Users", uniqueConstraints = @UniqueConstraint(columnNames = {"userID"}))
@PersistenceContext(type = PersistenceContextType.EXTENDED)
public class User {

    protected Integer userId;
    protected String username;
    protected String email;
    protected String password;
    protected Role role;
    protected Boolean isAccountSuspended;
    protected UserProfile userProfile;

    public User() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getUserID() {
        return userId;
    }

    //    @Size(max = 100)
    public String getUsername() {
        return username;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @Size(min = 8)
    public String getPassword() {
        return password;
    }

    //    @Size(max = 100)
    public String getEmail() {
        return email;
    }

    @Column(columnDefinition = "enum('ROLE_ADMIN','ROLE_CRITIC','ROLE_USER')")
    @Enumerated(EnumType.STRING)
    public Role getRole() {
        return role;
    }

    public Boolean getAccountSuspended() {
        return isAccountSuspended;
    }

    @JsonIgnore
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
