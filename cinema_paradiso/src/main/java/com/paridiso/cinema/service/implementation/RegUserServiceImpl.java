package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.constants.ExceptionConstants;
import com.paridiso.cinema.constants.TokenConstants;
import com.paridiso.cinema.entity.*;
import com.paridiso.cinema.entity.enumerations.Role;
import com.paridiso.cinema.persistence.*;

import com.paridiso.cinema.security.JwtTokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import com.paridiso.cinema.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
public class RegUserServiceImpl extends UserService {

    @Autowired
    private UtilityServiceImpl utilityService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private JwtTokenValidator validator;

    @Autowired
    WishListRepository wishListRepository;

    @Autowired
    WatchListRepository watchListRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    private TokenConstants tokenConstants;

    @Autowired
    ExceptionConstants exceptionConstants;

    @Transactional
    public Optional<User> signup(User user) {
        user.setRole(Role.ROLE_USER);
        user.setAccountSuspended(false);
        user.setPassword(utilityService.getHashedPassword(user.getPassword(), salt));

        if (userRepository.findUserByEmail(user.getEmail()) != null) {
            throw new ResponseStatusException(BAD_REQUEST, exceptionConstants.getUserExists());
        }

        // first create a user_profile for the user;
        user.setUserProfile(userProfileRepository.save(new UserProfile()));

        user.getUserProfile().setWishList(wishListRepository.save(new WishList()));
        user.getUserProfile().setWatchList(watchListRepository.save(new WatchList()));
        return Optional.ofNullable(userRepository.save(user));
    }

    @Transactional
    public UserProfile updateProfile(UserProfile userProfile) {
        UserProfile profile = userProfileRepository.findById(userProfile.getId())
                .orElseThrow(() -> new RuntimeException(exceptionConstants.getProfileNotFound() + userProfile.getId()));

        profile.setBiography(userProfile.getBiography());
        profile.setWatchList(userProfile.getWatchList());
        profile.setWishList(userProfile.getWishList());
        profile.setName(userProfile.getName());
        profile.setProfileImage(userProfile.getProfileImage());
        profile.setPrivate(userProfile.getPrivate());
        return userProfileRepository.save(profile);
    }

    @Transactional
    UserProfile makeUserCritic(Integer id) {
        UserProfile profile = userProfileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(exceptionConstants.getProfileNotFound() + id));

        profile.setCritic(true);
        return userProfileRepository.save(profile);
    }

    @Transactional
    public boolean makeSummaryPrivate(String jwtToken) {
        int typeLength = tokenConstants.getType().length();
        User validatedUser = validator.validate(jwtToken.substring(typeLength));
        UserProfile profile = userProfileRepository.findById(validatedUser.getUserProfile().getId())
                .orElseThrow(() -> new RuntimeException(exceptionConstants.getProfileNotFound()));

        profile.setPrivate(true);
        return userProfileRepository.save(profile).getPrivate();
    }

    @Transactional
    public boolean chagneProfilePicture(String jwtToken, MultipartFile file) throws IOException {
        int typeLength = tokenConstants.getType().length();
        User validatedUser = validator.validate(jwtToken.substring(typeLength));
        UserProfile profile = userProfileRepository.findById(validatedUser.getUserProfile().getId())
                .orElseThrow(() -> new RuntimeException("CANNOT FIND PROFILE"));
        profile.setProfileImage(validatedUser.getUserProfile().getId() + ".jpeg");
        userProfileRepository.save(profile);

        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get("avatars/" + profile.getId() + ".jpeg");
            System.out.println(path.toAbsolutePath().toString());
            Files.write(path, bytes);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public boolean updatePassword(Integer userId, String oldPassword, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getUserNotFound()));
        String hashedPassword = utilityService.getHashedPassword(oldPassword, salt);
        if (!hashedPassword.equals(user.getPassword())) {
            return false;
        } else {
            user.setPassword(utilityService.getHashedPassword(newPassword, salt));
            return userRepository.save(user).getPassword() != null;
        }
    }

    @Transactional
    public boolean checkUserNameTaken(String userName) {
        return userRepository.findUserByUsername(userName) != null;
    }

    @Transactional
    public boolean checkEmailTaken(String email) {
        return userRepository.findUserByEmail(email) != null;
    }

    @Transactional
    public UserProfile getProfile(String jwtToken) {
        int typeLength = tokenConstants.getType().length();
        User validatedUser = validator.validate(jwtToken.substring(typeLength));

        System.out.println(validatedUser.getUserID());
        System.out.println(validatedUser.getUserProfile().getId());

        return userProfileRepository.findById(validatedUser.getUserProfile().getId())
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getProfileNotFound()));
    }
}

