package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.entity.*;
import com.paridiso.cinema.entity.enumerations.Role;
import com.paridiso.cinema.persistence.UserProfileRepository;

import com.paridiso.cinema.persistence.WatchListRepository;
import com.paridiso.cinema.persistence.WishListRepository;
import com.paridiso.cinema.security.JwtTokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import com.paridiso.cinema.persistence.UserRepository;
import com.paridiso.cinema.service.UserService;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
public class RegUserServiceImpl extends UserService {

    @Autowired
    private UtilityServiceImpl utilityService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private Environment environment;

    @Autowired
    private JwtTokenValidator validator;

    @Autowired
    WishListRepository wishListRepository;

    @Autowired
    WatchListRepository watchListRepository;


    @Transactional
    public Optional<User> signup(User user) {
        user.setRole(Role.ROLE_USER);
        user.setAccountSuspended(false);
        user.setPassword(utilityService.getHashedPassword(user.getPassword(), salt));

        if (userRepository.findUserByEmail(user.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "USER EXISTS");
        }

        // first create a user_profile for the user;
        user.setUserProfile(userProfileRepository.save(new UserProfile()));
        user.getUserProfile().setWishList(wishListRepository.save(new WishList()));
        user.getUserProfile().setWishList(watchListRepository.save(new WishList()));
        return Optional.ofNullable(userRepository.save(user));

    }


    public UserProfile updateProfile(UserProfile userProfile) {

        UserProfile profile = userProfileRepository.findById(userProfile.getId())
                .orElseThrow(() -> new RuntimeException("CANNOT FIND PROFILE"));

        profile.setCritic(userProfile.getCritic());
        profile.setBiography(userProfile.getBiography());
        profile.setWatchList(userProfile.getWatchList());
        profile.setWishList(userProfile.getWishList());
        profile.setName(userProfile.getName());
        profile.setProfileImage(userProfile.getProfileImage());
        profile.setPrivate(userProfile.getPrivate());
        return userProfileRepository.save(profile);
    }

    @Transactional
    public boolean makeSummaryPrivate(String jwtToken) {
        int headerLength = environment.getProperty("token.type").length();
        User validatedUser = validator.validate(jwtToken.substring(headerLength));

        UserProfile profile = userProfileRepository.findById(validatedUser.getUserProfile().getId())
                .orElseThrow(() -> new RuntimeException("CANNOT FIND PROFILE"));

        profile.setPrivate(true);
        return userProfileRepository.save(profile).getPrivate() == true ? true : false;
    }

    @Transactional
    public boolean updatePassword(Integer userId, String oldPassword, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, "USER NOT FOUND"));

        String hashedPassword = utilityService.getHashedPassword(oldPassword, salt);
        if (!hashedPassword.equals(user.getPassword())) {
            return false;
        } else {
            user.setPassword(utilityService.getHashedPassword(newPassword, salt));
            return userRepository.save(user).getPassword() != null ? true : false;
        }
    }

    @Transactional
    public boolean checkUserNameTaken(String userName) {
        return userRepository.findUserByUsername(userName) != null ? true : false;
    }

    @Transactional
    public boolean checkEmailTaken(String email) {
        return userRepository.findUserByEmail(email) != null ? true : false;
    }

}

