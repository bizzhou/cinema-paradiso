package com.rottentomatoes.service.impl;

import com.rottentomatoes.dao.UserDao;
import com.rottentomatoes.model.User;
import com.rottentomatoes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public User saveUser(User user) {
        return userDao.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    @Transactional
    @Override
    public List<User> getUsers() {
        return userDao.findAll();
    }

    @Transactional
    @Override
    public Optional<User> getUserById(Integer userId) {
        return userDao.findById(userId);
    }

}
