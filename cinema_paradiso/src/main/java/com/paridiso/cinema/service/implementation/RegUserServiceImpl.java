package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.entity.User;
import com.paridiso.cinema.entity.UserProfile;
import com.paridiso.cinema.entity.enumerations.Role;
import com.paridiso.cinema.persistence.UserProfileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import com.paridiso.cinema.persistence.UserRepository;
import com.paridiso.cinema.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class RegUserServiceImpl extends UserService {

    @Autowired
    UtilityServiceImpl utilityService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Transactional
    public Optional<User> signup(User user) {

        user.setRole(Role.ROLE_USER);
        user.setAccountSuspended(false);
        user.setPassword(utilityService.getHashedPassword(user.getPassword(), salt));

        if (userRepository.findUserByEmail(user.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "USER EXISTS");
        }

        // UxserProfile p = userProfileRepository.save(new UserProfile());
        // user.setUserProfile(p);

        return Optional.ofNullable(userRepository.save(user));

    }

    public boolean updateProfile(UserProfile userProfile) {
        return false;
    }

    @Transactional
    public boolean makeSummaryPrivate(Integer userId) {
        return false;
    }

    public boolean updatePassword(String newPassword) {
        return false;
    }


}
