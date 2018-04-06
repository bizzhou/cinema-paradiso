package com.paridiso.cinema.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.paridiso.cinema.entity.User;
import com.paridiso.cinema.entity.UserProfile;
import com.paridiso.cinema.security.JwtTokenGenerator;
import com.paridiso.cinema.security.JwtTokenValidator;
import com.paridiso.cinema.security.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import com.paridiso.cinema.service.implementation.RegUserServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RequestMapping("/user")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RegUserController {

    @Autowired
    private RegUserServiceImpl userService;

    @Autowired
    private JwtTokenGenerator generator;

    @Autowired
    private JwtTokenValidator validator;

    @Autowired
    private Environment environment;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping(value = "/login")
    public ResponseEntity<JwtUser> userLogin(@RequestParam(value = "email", required = true) String email,
                                             @RequestParam(value = "password", required = true) String password) {
        User user = userService.login(email, password).orElseThrow(() ->
                new ResponseStatusException(BAD_REQUEST, "USER NOT FOUND"));
        JwtUser jwtUser = new JwtUser(user.getUsername(), generator.generate(user), user.getUserID(), user.getRole());
        return ResponseEntity.ok(jwtUser);
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<Boolean> userLogout() {
        return ResponseEntity.ok(true);
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<JwtUser> userSignup(@RequestBody User user) {
        User optionalUser = userService.signup(user).orElseThrow(() ->
                new ResponseStatusException(BAD_REQUEST, "USER ALREADY EXISTS"));
        JwtUser jwtUser = new JwtUser(optionalUser.getUsername(),
                generator.generate(optionalUser), optionalUser.getUserID(), optionalUser.getRole());
        return ResponseEntity.ok(jwtUser);
    }

    @PostMapping(value = "/check/username/{username}")
    public ResponseEntity<?> checkUsername(@PathVariable String username) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        boolean nameTaken = userService.checkUserNameTaken(username);
        objectNode.put("taken", nameTaken);
        return ResponseEntity.ok(objectNode);
    }

    @PostMapping(value = "/check/email/{email}")
    public ResponseEntity<?> checkUserEmail(@PathVariable String email) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        boolean nameTaken = userService.checkEmailTaken(email);
        objectNode.put("taken", nameTaken);
        return ResponseEntity.ok(objectNode);
    }

    @PostMapping(value = "/change/password")
    public ResponseEntity<?> changePassword(@RequestHeader(value = "Authorization") String jwtToken,
                                            @RequestParam(value = "old_password", required = true) String oldPass,
                                            @RequestParam(value = "new_password", required = true) String newPass) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        int headerLength = environment.getProperty("token.type").length();
        User validatedUser = validator.validate(jwtToken.substring(headerLength));
        objectNode.put("success", userService.updatePassword(validatedUser.getUserID(), oldPass, newPass));
        return ResponseEntity.ok(objectNode);
    }

    // TODO: Sending image to the backend....

    //    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(value = "/protected/change/avatar")
    public ResponseEntity<Boolean> changeProfilePicture(@RequestHeader(value = "Authorization") String jwtToken) {
        int headerLength = environment.getProperty("token.type").length();
        User validatedUser = validator.validate(jwtToken.substring(headerLength));

        // Now you can get the user information with the data.
        return ResponseEntity.ok(true);
    }

    @GetMapping(value = "/avatar/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getAvatar(@PathVariable String fileName) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("images/" + fileName);
        byte[] bytes = StreamUtils.copyToByteArray(classPathResource.getInputStream());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<?> upload(@RequestParam MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            FileCopyUtils.copy(file.getBytes(), new File("src/main/resources/static/images/" + fileName));
            return ResponseEntity.ok("UPLOAD SUCCESS");
        } else {
            return ResponseEntity.badRequest().body("UPLOAD FAILURE ......");
        }
    }


    @PostMapping(value = "/forgot/password")
    public ResponseEntity<User> verifyCritic(@RequestParam(value = "email", required = true) String email) {
        userService.forgotPassword(email);
        return null;
    }

    @PostMapping(value = "/update/profile")
    public ResponseEntity<?> updateProfile(@RequestBody UserProfile userProfile) {
        UserProfile newProfile = userService.updateProfile(userProfile);
        System.out.println(newProfile);
        return new ResponseEntity<>(newProfile, HttpStatus.OK);
    }


    @PostMapping(value = "/set/private")
    public ResponseEntity<?> makeSummaryPrivate(@RequestHeader(value = "Authorization") String jwtToken) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("success", userService.makeSummaryPrivate(jwtToken));
        return ResponseEntity.ok(objectNode);
    }

}



