package com.rottentomatoes.service;

import com.rottentomatoes.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public User saveUser(User user);

    public void deleteUser(User user);

    public List<User> getUsers();

    public Optional<User> getUserById(Integer userId);


}
