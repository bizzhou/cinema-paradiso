package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.entity.User;
import com.paridiso.cinema.entity.UserProfile;
import com.paridiso.cinema.entity.enumerations.Role;
import com.paridiso.cinema.persistence.UserProfileRepository;
import com.paridiso.cinema.persistence.UserRepository;
import com.paridiso.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
public class AdminServiceImpl extends UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getRole().equals(Role.ROLE_CRITIC) || user.getRole().equals(Role.ROLE_USER))
                .collect(Collectors.toList());
    }

    public boolean suspendUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, "CANNOT FIND USER"));
        user.setAccountSuspended(true);
        return userRepository.save(user) != null ? true : false;
    }

}
