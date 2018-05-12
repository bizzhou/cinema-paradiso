package com.paridiso.cinema.controller;

import com.paridiso.cinema.constants.ExceptionConstants;
import com.paridiso.cinema.entity.CriticApplication;
import com.paridiso.cinema.entity.User;
import com.paridiso.cinema.security.JwtTokenGenerator;
import com.paridiso.cinema.security.JwtUser;
import com.paridiso.cinema.service.implementation.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@CrossOrigin(origins = "*")
@RequestMapping("/admin")
@RestController
public class AdminController {

    @Autowired
    private AdminServiceImpl userService;

    @Autowired
    private JwtTokenGenerator generator;

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

    @DeleteMapping(value = "/suspend/{id}")
    public ResponseEntity<?> suspendUser(@PathVariable Integer id) {
        Boolean result = userService.suspendUser(id);
        return ResponseEntity.ok(result);
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/get/users")
    public ResponseEntity<?> getUsers() {
        HashMap<String, List<User>> userMap = new HashMap<>();
        userMap.put("users", userService.getAllUsers());
        return ResponseEntity.ok(userMap);
    }

    @PostMapping(value = "/verify/critic")
    public ResponseEntity<?> verifyCritic(@RequestParam Integer userId) {
        userService.makeCritic(userId);
        return ResponseEntity.ok(true);
    }

    @GetMapping(value = "/get/critic_applications")
    public ResponseEntity<?> getCritcApplications() {
        List<CriticApplication> allCriticApplication = userService.getAllCriticApplication();
        List<HashMap> list = new ArrayList<>();
        for (CriticApplication application : allCriticApplication) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("username", application.getUser().getUsername());
            hashMap.put("email", application.getUser().getEmail());
            hashMap.put("id", application.getUser().getUserID());
            hashMap.put("reason", application.getReason());
            list.add(hashMap);
        }
        return ResponseEntity.ok(list);
    }


    @PostMapping(value = "upload/images")
    public void uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,
                                    @RequestParam("movie") String movie) {
        System.out.println(movie);
        for (MultipartFile file : files) {
            userService.saveFile(file, movie);
        }
    }

    @PostMapping(value = "upload/poster")
    public String uploadPoster(@RequestParam("file") MultipartFile file, String movie) {
        String name = userService.saveFile(file, movie);
        return name.equals("") ? "FALSE" : name;
    }

    @GetMapping(value = "/images/{folder}/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getAvatar(@PathVariable String folder, @PathVariable String imageName) throws IOException {
        String fileLocation = Paths.get("images/" + folder + "/" + imageName).toAbsolutePath().toString();
        System.out.println(fileLocation);
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

}
