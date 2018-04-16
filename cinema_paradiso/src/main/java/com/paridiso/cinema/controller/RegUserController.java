package com.paridiso.cinema.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.paridiso.cinema.constants.ExceptionConstants;
import com.paridiso.cinema.constants.TokenConstants;
import com.paridiso.cinema.entity.User;
import com.paridiso.cinema.entity.UserProfile;
import com.paridiso.cinema.security.JwtTokenGenerator;
import com.paridiso.cinema.security.JwtTokenValidator;
import com.paridiso.cinema.security.JwtUser;
import com.paridiso.cinema.service.implementation.RegUserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.HashMap;

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
    private TokenConstants tokenConstants;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    ExceptionConstants exceptionConstants;

    private static final Logger logger = LogManager.getLogger(RegUserController.class);

    @PostMapping(value = "/login")
    public ResponseEntity<JwtUser> userLogin(@RequestParam(value = "email") String email,
                                             @RequestParam(value = "password") String password, HttpSession session) {
        User user = userService.login(email, password).orElseThrow(() ->
                new ResponseStatusException(BAD_REQUEST, exceptionConstants.getUserNotFound()));
        JwtUser jwtUser = new JwtUser(user.getUsername(), generator.generate(user), user.getUserID(), user.getRole());
        session.setAttribute("user", user);
        System.out.println(session.getAttribute("user"));
        return ResponseEntity.ok(jwtUser);
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<Boolean> userLogout() {
        return ResponseEntity.ok(true);
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<JwtUser> userSignup(@RequestBody User user, HttpSession session) {
        User optionalUser = userService.signup(user).orElseThrow(() ->
                new ResponseStatusException(BAD_REQUEST, exceptionConstants.getUserExists()));
        JwtUser jwtUser = new JwtUser(optionalUser.getUsername(),
                generator.generate(optionalUser), optionalUser.getUserID(), optionalUser.getRole());
        session.setAttribute("user", user);
        System.out.println(session.getAttribute("user"));
        return ResponseEntity.ok(jwtUser);
    }

    @GetMapping(value = "/check/username/{username}")
    public ResponseEntity<?> checkUsername(@PathVariable String username) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        boolean nameTaken = userService.checkUserNameTaken(username);
        objectNode.put("taken", nameTaken);
        return ResponseEntity.ok(objectNode);
    }

    @GetMapping(value = "/check/email/{email}")
    public ResponseEntity<?> checkUserEmail(@PathVariable String email) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        boolean nameTaken = userService.checkEmailTaken(email);
        objectNode.put("taken", nameTaken);
        return ResponseEntity.ok(objectNode);
    }

    @PostMapping(value = "/change/password")
    public ResponseEntity<?> changePassword(@RequestHeader(value = "Authorization") String jwtToken,
                                            @RequestParam(value = "old_password") String oldPass,
                                            @RequestParam(value = "new_password") String newPass) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        int typeLength = tokenConstants.getType().length();
        User validatedUser = validator.validate(jwtToken.substring(typeLength));
        objectNode.put("success", userService.updatePassword(validatedUser.getUserID(), oldPass, newPass));
        return ResponseEntity.ok(objectNode);
    }

    @GetMapping(value = "/get/profile")
    public ResponseEntity<?> getProfile(@RequestHeader(value = "Authorization") String jwtToken, HttpSession session) throws JsonProcessingException {
        System.out.println((User) session.getAttribute("user"));
        UserProfile profile = userService.getProfile(jwtToken);

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();

        objectObjectHashMap.put("name", profile.getName());
        objectObjectHashMap.put("id", profile.getId());
        if (profile.getProfileImage() == null) {
            objectObjectHashMap.put("profileImage", "default.jpeg");
        } else {
            objectObjectHashMap.put("profileImage", profile.getProfileImage());
        }
        objectObjectHashMap.put("biography", profile.getBiography());
        objectObjectHashMap.put("isCritic", profile.getCritic());
        objectObjectHashMap.put("wishList", profile.getWishList().getMovies());

        return ResponseEntity.ok(objectObjectHashMap);
    }


    @GetMapping(value = "/avatar/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getAvatar(@PathVariable String fileName) throws IOException {
        String fileLocation = Paths.get("avatars/" + fileName).toAbsolutePath().toString();
        logger.info(fileLocation);
        try {
            File file = new File(fileLocation);
            InputStream inputStream = new FileInputStream(file);
            byte[] bytes = StreamUtils.copyToByteArray(inputStream);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exceptionConstants.getResourceNotFound());
        }
    }

    @PostMapping(value = "/update/avatar")
    public ResponseEntity<?> changeAvatar(@RequestParam MultipartFile file,
                                          @RequestHeader(value = "Authorization") String jwtToken) throws IOException {
        return ResponseEntity.ok(userService.chagneProfilePicture(jwtToken, file));
    }


    @PostMapping(value = "/forgot/password")
    public ResponseEntity<User> verifyCritic(@RequestParam(value = "email") String email) {
        userService.forgotPassword(email);
        return null;
    }

    @PostMapping(value = "/update/profile")
    public ResponseEntity<?> updateProfile(@RequestBody UserProfile userProfile) {
        UserProfile newProfile = userService.updateProfile(userProfile);
        return new ResponseEntity<>(newProfile, HttpStatus.OK);
    }

    @PostMapping(value = "/set/private")
    public ResponseEntity<?> makeSummaryPrivate(@RequestHeader(value = "Authorization") String jwtToken) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("success", userService.makeSummaryPrivate(jwtToken));
        return ResponseEntity.ok(objectNode);
    }
}



