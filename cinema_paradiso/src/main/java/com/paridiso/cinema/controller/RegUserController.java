package com.paridiso.cinema.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.paridiso.cinema.constants.ExceptionConstants;
import com.paridiso.cinema.entity.CriticApplication;
import com.paridiso.cinema.entity.User;
import com.paridiso.cinema.entity.UserProfile;
import com.paridiso.cinema.entity.UserRating;
import com.paridiso.cinema.security.JwtTokenGenerator;
import com.paridiso.cinema.security.JwtUser;
import com.paridiso.cinema.service.JwtTokenService;
import com.paridiso.cinema.service.implementation.RegUserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RequestMapping("/user")
@RestController
@CrossOrigin(origins = "*")
public class RegUserController {
    @Autowired
    private RegUserServiceImpl userService;

    @Autowired
    private JwtTokenGenerator generator;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    ExceptionConstants exceptionConstants;

    private static final Logger logger = LogManager.getLogger(RegUserController.class);

    @PostMapping(value = "/login")
    public ResponseEntity<JwtUser> userLogin(@RequestParam(value = "email") String email,
                                             @RequestParam(value = "password") String password) {
        User user = userService.login(email, password).orElseThrow(() ->
                new ResponseStatusException(BAD_REQUEST, exceptionConstants.getUserNotFound()));
        JwtUser jwtUser = new JwtUser(user.getUsername(), generator.generate(user), user.getUserID(), user.getRole());
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
        Integer userId = jwtTokenService.getUserIdFromToken(jwtToken);
        logger.info(userId);
        boolean result = userService.updatePassword(userId, oldPass, newPass);
        return result ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @GetMapping(value = "/get/profile")
    public ResponseEntity<?> getProfile(@RequestHeader(value = "Authorization") String jwtToken, HttpSession session) throws JsonProcessingException {
        UserProfile profile = userService.getProfile(jwtTokenService.getUserProfileIdFromToken(jwtToken));
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
        objectObjectHashMap.put("isPrivate", profile.getPrivate());
        objectObjectHashMap.put("accountCreatedDate", profile.getAccountCreatedDate());
        objectObjectHashMap.put("wishList", profile.getWishList().getMovies());
        objectObjectHashMap.put("notInterestedList", profile.getNotInterestedList().getMovies());
        objectObjectHashMap.put("userRatings", userService.getUserRatings(jwtTokenService.getUserProfileIdFromToken(jwtToken)));
        return ResponseEntity.ok(objectObjectHashMap);
    }

    @PostMapping(value = "/get/publicProfile")
    public ResponseEntity<?> getProfileByUsername(@RequestParam String username) {
        return new ResponseEntity<Object>(userService.getProfileByUsername(username), HttpStatus.OK);
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
        Integer id = jwtTokenService.getUserProfileIdFromToken(jwtToken);
        return ResponseEntity.ok(userService.chagneProfilePicture(id, file));
    }


    @PostMapping(value = "/forgot/password")
    public ResponseEntity<User> verifyCritic(@RequestParam(value = "email") String email) {
        userService.forgotPassword(email);
        return null;
    }

    @PostMapping(value = "/update/profile")
    public ResponseEntity<?> updateProfile(@RequestHeader(value = "Authorization") String jwtToken,
                                           @RequestBody UserProfile userProfile) {
        UserProfile newProfile = userService.updateProfile(userProfile);
        return new ResponseEntity<>(newProfile, HttpStatus.OK);
    }

    @PostMapping(value = "/set/private")
    public ResponseEntity<?> makeSummaryPrivate(@RequestHeader(value = "Authorization") String jwtToken) {
        boolean result = userService.makeSummaryPrivate(jwtTokenService.getUserProfileIdFromToken(jwtToken));
        return result ? ResponseEntity.ok(result) : ResponseEntity.badRequest().body(result);
    }

    @GetMapping(value = "/get/ratings")
    public ResponseEntity getRatings(@RequestHeader(value = "Authorization") String jwtToken) {
        Integer userProfileIdFromToken = jwtTokenService.getUserProfileIdFromToken(jwtToken);
        List<UserRating> ratingList = userService.getUserRatings(userProfileIdFromToken);
        return ResponseEntity.ok(ratingList);
    }

    @PostMapping(value = "/setPrivate")
    public ResponseEntity<Boolean> setPrivate(@RequestHeader(value = "Authorization") String jwtToken,
                                              @RequestParam Boolean isPrivate) {
        Boolean result = userService.setPrivate(jwtTokenService.getUserProfileIdFromToken(jwtToken), isPrivate);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "submit/critic_application")
    public ResponseEntity<?> submitCritc(@RequestHeader(value = "Authorization") String jwtToken,
                                         @RequestParam(value = "reason") String reason) {
        CriticApplication criticApplication = new CriticApplication();
        criticApplication.setApproved(false);
        criticApplication.setReason(reason);
        userService.saveCriticAppliction(jwtTokenService.getUserIdFromToken(jwtToken), criticApplication);
        return ResponseEntity.ok(true);
    }

}



