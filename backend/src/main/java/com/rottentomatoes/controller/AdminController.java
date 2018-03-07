package com.rottentomatoes.controller;

import com.rottentomatoes.dao.UserDao;
import com.rottentomatoes.exception.ResourceNotFoundException;
import com.rottentomatoes.model.User;
import com.rottentomatoes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * Administrator controller, receiving/sending requests related to administrator
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    @Qualifier(value="adminServiceImpl")
    UserService userService;

    // temp method for testing purpose
    @RequestMapping
    public String welcome() {
        return "Cinema Paradiso Welcomes You!";
    }

    // temp method for testing purpose
    @PostMapping("/users")
    public User createUser(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }

    /**
     * Get all users
     * @return a list of users
     */
    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    /**
     * Get user by user id
     * @param userId user id
     * @return user
     */
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable(value = "id") Integer userId) {
        return userService.getUserById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }

    // temp method for testing purpose
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Integer userId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        userService.deleteUser(user);

        return ResponseEntity.ok().build();
    }

}
