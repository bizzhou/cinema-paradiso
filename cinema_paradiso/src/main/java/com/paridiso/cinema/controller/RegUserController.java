package com.paridiso.cinema.controller;

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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import com.paridiso.cinema.service.implementation.RegUserServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.web.bind.annotation.RequestMethod.*;

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

    @RequestMapping(value = "/login", method = POST)
    public ResponseEntity<JwtUser> userLogin(@RequestParam(value = "email", required = true) String email,
                                             @RequestParam(value = "password", required = true) String password) {
        User user = userService.login(email, password).orElseThrow(() ->
                new ResponseStatusException(BAD_REQUEST, "USER NOT FOUND"));
        JwtUser jwtUser = new JwtUser(user.getUsername(), generator.generate(user), user.getUserID(), user.getRole());

        return ResponseEntity.ok(jwtUser);
    }

    @RequestMapping(value = "/logout", method = POST)
    public ResponseEntity<Boolean> userLogout() {
        return ResponseEntity.ok(true);
    }

    @RequestMapping(value = "/signup", method = POST)
    public ResponseEntity<JwtUser> userSignup(@RequestBody User user) {
        User optionalUser = userService.signup(user).orElseThrow(() ->
                new ResponseStatusException(BAD_REQUEST, "USER ALREADY EXISTS"));
        JwtUser jwtUser = new JwtUser(optionalUser.getUsername(),
                generator.generate(optionalUser), optionalUser.getUserID(), optionalUser.getRole());

        return ResponseEntity.ok(jwtUser);
    }

    @RequestMapping(value = "/check_username_taken/{username}", method = POST)
    public ResponseEntity<Boolean> checkUsername(@PathVariable String username) {
        return null;
    }

    @RequestMapping(value = "/check_email_taken/{email}", method = POST)
    public ResponseEntity<Boolean> checkUserEmail(@PathVariable String email) {
        return null;
    }

    @RequestMapping(value = "/change_password", method = POST)
    public ResponseEntity<Boolean> changePassword(@RequestParam(value = "old_password", required = true) String oldPass,
                                                  @RequestParam(value = "new_password", required = true) String newPass) {
        return null;
    }

    // TODO: Sending image to the backend....

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/protected/change_profile_picture", method = POST)
    public ResponseEntity<Boolean> changeProfilePicture(@RequestHeader(value = "Authorization") String jwtToken) {
        int headerLength = environment.getProperty("token.type").length();
        User validatedUser = validator.validate(jwtToken.substring(headerLength));
        System.out.println(validatedUser);
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
            InputStream is = file.getInputStream();

            Files.copy(is, Paths.get("/src/main/resources/static/images" + fileName),
                    StandardCopyOption.REPLACE_EXISTING);

            return ResponseEntity.ok("UPLOAD SUCCESS");
        } else {
            return ResponseEntity.badRequest().body("UPLOAD FAILURE ......");
        }
    }


    @RequestMapping(value = "/forgot_password", method = POST)
    public ResponseEntity<User> verifyCritic(@RequestParam(value = "email", required = true) String email) {
        userService.forgotPassword(email);
        return null;
    }

    @RequestMapping(value = "/update_profile", method = POST)
    public ResponseEntity<?> updateProfile(@RequestBody UserProfile userProfile) {
        UserProfile newProfile = userService.updateProfile(userProfile);
        return new ResponseEntity<>(newProfile, HttpStatus.OK);
    }


    @RequestMapping(value = "/mark_private", method = POST)
    public ResponseEntity<Boolean> makeSummaryPrivate() {
        return null;
    }

}



