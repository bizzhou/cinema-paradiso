package com.paridiso.cinema.security;

import com.paridiso.cinema.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtLogInProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private JwtTokenValidator validator;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
    }


    @Override
    protected UserDetails retrieveUser(String s,
                                       UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

        JwtToken jwtToken = (JwtToken) usernamePasswordAuthenticationToken;

        // validate the token send after user has login
        User jwtUser = validator.validate(jwtToken.getToken());

        if (jwtUser == null) {
            throw new RuntimeException(("TOKEN INVALID"));
        }

        // Get the role of the user
        List<GrantedAuthority> grantedAuthorityList = AuthorityUtils
                .commaSeparatedStringToAuthorityList(jwtUser.getRole().name());


        return new JwtUserDetails(jwtUser.getUsername(), jwtUser.getUserID(), jwtUser.getRole(),
                jwtToken.getToken(), grantedAuthorityList);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtToken.class.isAssignableFrom(authentication));
    }

}
