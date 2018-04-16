package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.constants.ExceptionConstants;
import com.paridiso.cinema.entity.User;
import com.paridiso.cinema.entity.enumerations.Role;
import com.paridiso.cinema.persistence.UserProfileRepository;
import com.paridiso.cinema.persistence.UserRepository;
import com.paridiso.cinema.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
public class AdminServiceImpl extends UserService {

    private UserRepository userRepository;
    private UserProfileRepository userProfileRepository;
    private ExceptionConstants exceptionConstants;

    public AdminServiceImpl(UserRepository userRepository, UserProfileRepository userProfileRepository,
                            ExceptionConstants exceptionConstants) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
        this.exceptionConstants = exceptionConstants;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getRole().equals(Role.ROLE_CRITIC) || user.getRole().equals(Role.ROLE_USER))
                .collect(Collectors.toList());
    }

    public boolean suspendUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getUserNotFound()));
        user.setAccountSuspended(true);
        return userRepository.save(user).getUserID() == null;
    }

}
