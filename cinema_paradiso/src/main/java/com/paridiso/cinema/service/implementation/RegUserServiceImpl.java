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

        // first create a user_profile for the user;
        user.setUserProfile(userProfileRepository.save(new UserProfile()));
        return Optional.ofNullable(userRepository.save(user));
    }

    @Transactional
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
    public UserProfile makeSummaryPrivate(Integer profileId) {
        UserProfile profile = userProfileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("CANNOT FIND PROFILE"));

        profile.setPrivate(true);
        return userProfileRepository.save(profile);
    }

    @Transactional
    public User updatePassword(Integer userId, String oldPassword, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("USER NOT FOUND"));

        System.out.println(user);
        String hashedPassword = utilityService.getHashedPassword(oldPassword, salt);
        System.out.println(oldPassword);
        System.out.println(salt);
        System.out.println(hashedPassword);

        if (!hashedPassword.equals(user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "WRONG PASSWORD");
        } else {
            user.setPassword(utilityService.getHashedPassword(newPassword, salt));
            return userRepository.save(user);
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
