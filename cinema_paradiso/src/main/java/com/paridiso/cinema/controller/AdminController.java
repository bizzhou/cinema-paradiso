package com.paridiso.cinema.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.paridiso.cinema.constants.ExceptionConstants;
import com.paridiso.cinema.entity.CriticApplication;
import com.paridiso.cinema.entity.User;
import com.paridiso.cinema.security.JwtTokenGenerator;
import com.paridiso.cinema.security.JwtUser;
import com.paridiso.cinema.service.implementation.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RequestMapping("/admin")
@RestController
public class AdminController {

    @Autowired
    private AdminServiceImpl userService;

    @Autowired
    private JwtTokenGenerator generator;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    ExceptionConstants exceptionConstants;

    @RequestMapping(value = "/login", method = POST)
    public ResponseEntity<JwtUser> adminLogin(@RequestParam(value = "email") String email,
                                              @RequestParam(value = "password") String password) {
        User user = userService.login(email, password).orElseThrow(() ->
                new ResponseStatusException(BAD_REQUEST, exceptionConstants.getUserNotFound()));
        JwtUser jwtUser = new JwtUser(user.getUsername(), generator.generate(user), user.getUserID(), user.getRole());
        return ResponseEntity.ok(jwtUser);
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<Boolean> adminLogout() {
        return null;
    }

    @PostMapping(value = "/suspend/{id}")
    public ResponseEntity<?> suspendUser(@PathVariable Integer id) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("success", userService.suspendUser(id));
        return ResponseEntity.ok(objectNode);
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/get/users")
    public ResponseEntity<?> getUsers() {
        HashMap<String, List<User>> userMap = new HashMap<>();
        userMap.put("users", userService.getAllUsers());
        return ResponseEntity.ok(userMap);
    }

    @PostMapping(value = "/verify/critic")
    public ResponseEntity<Boolean> verifyCritic(@RequestParam Integer userID) {
        return null;
    }

}
