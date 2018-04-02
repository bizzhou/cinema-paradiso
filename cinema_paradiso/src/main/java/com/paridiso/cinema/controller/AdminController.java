package com.paridiso.cinema.controller;


import com.paridiso.cinema.entity.CriticApplication;
import com.paridiso.cinema.security.JwtTokenGenerator;
import com.paridiso.cinema.security.JwtUser;
import com.paridiso.cinema.entity.User;
import com.paridiso.cinema.service.implementation.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RequestMapping("/admin")
@RestController
public class AdminController {

    @Autowired
    private AdminServiceImpl userService;

    @Autowired
    private JwtTokenGenerator generator;

    @RequestMapping(value = "/login", method = POST)
    public ResponseEntity<JwtUser> adminLogin(@RequestParam(value = "email", required = true) String email,
                                              @RequestParam(value = "password", required = true) String password) {
        User user = userService.login(email, password).orElseThrow(() ->
                new ResponseStatusException(BAD_REQUEST, "USER NOT FOUND"));
        JwtUser jwtUser = new JwtUser(user.getUsername(), generator.generate(user), user.getUserID(), user.getRole());

        return ResponseEntity.ok(jwtUser);
    }

    @RequestMapping(value = "/logout", method = POST)
    public ResponseEntity<Boolean> adminLogout() {
        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/protected/suspend/{id}", method = POST)
    public ResponseEntity<Boolean> suspendUser(@PathVariable Integer id) {
        return null;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/protected/all_user", method = GET)
    public ResponseEntity<List> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @RequestMapping(value = "/verify_critic", method = POST)
    public ResponseEntity<Boolean> verifyCritic(@RequestBody CriticApplication application) {
        return null;
    }

}
