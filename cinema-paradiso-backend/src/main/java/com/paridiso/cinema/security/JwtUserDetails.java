package com.paridiso.cinema.security;


import com.paridiso.cinema.entity.enumerations.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class JwtUserDetails extends JwtUser implements UserDetails {


    private Collection<? extends GrantedAuthority> authorities;

    public JwtUserDetails(String username, Integer id, Role role, String token,
                          List<GrantedAuthority> grantedAuthorities) {
        super(username, token, id, role);
        this.authorities = grantedAuthorities;

    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
