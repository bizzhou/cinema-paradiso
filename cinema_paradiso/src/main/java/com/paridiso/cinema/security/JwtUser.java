package com.paridiso.cinema.security;

import com.paridiso.cinema.entity.enumerations.Role;

public class JwtUser {

    protected String username;
    protected String token;
    protected Integer id;
    protected Role role;

    public JwtUser(String username, String token, Integer id, Role role) {
        this.username = username;
        this.token = token;
        this.id = id;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}
