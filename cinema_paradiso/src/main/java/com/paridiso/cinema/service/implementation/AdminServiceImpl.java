package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.entity.User;
import com.paridiso.cinema.entity.enumerations.Role;
import com.paridiso.cinema.persistence.UserDao;
import com.paridiso.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl extends UserService {

    @Autowired
    UserDao userDao;

    public List<User> getAllUsers() {
        return userDao.findAll()
                .stream()
                .filter(user -> user.getRole().equals(Role.ROLE_CRITIC) || user.getRole().equals(Role.ROLE_USER))
                .collect(Collectors.toList());
    }


}
