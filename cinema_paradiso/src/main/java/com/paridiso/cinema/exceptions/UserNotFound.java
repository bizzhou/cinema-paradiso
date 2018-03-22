package com.paridiso.cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFound extends RuntimeException {

    private String email;

    public UserNotFound(String email) {
        super(String.format("CANNOT FIND %s", email));
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

}
