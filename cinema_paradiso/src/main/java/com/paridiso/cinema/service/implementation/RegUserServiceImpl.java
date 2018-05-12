package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.constants.ExceptionConstants;
import com.paridiso.cinema.constants.LimitationConstants;
import com.paridiso.cinema.entity.*;
import com.paridiso.cinema.entity.enumerations.Role;
import com.paridiso.cinema.persistence.*;
import com.paridiso.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
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
    WishListRepository wishListRepository;

    @Autowired
    NotInterestedListRepository notInterestedListRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    CriticApplictionRepository criticApplictionRepository;

    @Autowired
    ExceptionConstants exceptionConstants;

    @Autowired
    LimitationConstants limitationConstants;

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
        // create a new wish list/ not interested list
        user.getUserProfile().setUsername(user.getUsername());
        // create a new wish list/ watch list
        user.getUserProfile().setCritic(false);
        user.getUserProfile().setPrivate(false);
        user.getUserProfile().setAccountCreatedDate(Calendar.getInstance());
        user.getUserProfile().setWishList(wishListRepository.save(new WishList()));
        user.getUserProfile().setNotInterestedList(notInterestedListRepository.save(new NotInterestedList()));
        // set size limit
        user.getUserProfile().getWishList().setWishListSize(limitationConstants.getWishListSize());
        user.getUserProfile().getNotInterestedList().setNotInterestedListSize(limitationConstants.getWishListSize());
        userProfileRepository.save(user.getUserProfile());
        return Optional.ofNullable(userRepository.save(user));
    }

    @Transactional
    public UserProfile updateProfile(HashMap<String, String> userProfile) {
        System.out.println(userProfile);
        UserProfile profile = getUserProfile(Integer.parseInt(userProfile.get("id").toString()));
        User user = userRepository.findUserByUserProfile(profile);
        if (userRepository.findUserByEmail(userProfile.get("email")) != null) {
            throw new ResponseStatusException(BAD_REQUEST, exceptionConstants.getUserExists());
        }
        if (userRepository.findUserByUsername(userProfile.get("username")) != null) {
            throw new ResponseStatusException(BAD_REQUEST, exceptionConstants.getUserExists());
        }
        user.setEmail(userProfile.get("email"));
        user.setUsername(userProfile.get("username"));
        profile.setUsername(userProfile.get("username"));
        profile.setBiography(userProfile.get("biography"));
        user.setUserProfile(profile);
        System.out.println(user.getEmail());
        return userRepository.save(user).getUserProfile();
    }

    @Transactional
    public boolean makeSummaryPrivate(Integer profileId) {
        UserProfile profile = utilityService.getUserProfile(profileId);
        profile.setPrivate(true);
        return userProfileRepository.save(profile).getPrivate();
    }

    @Transactional
    public boolean chagneProfilePicture(Integer profileId, MultipartFile file) throws IOException {
        UserProfile profile = utilityService.getUserProfile(profileId);
        profile.setProfileImage(profileId + ".jpeg");
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
        User user = utilityService.getUser(userId);
        String hashedPassword = utilityService.getHashedPassword(oldPassword, salt);
        if (!hashedPassword.equals(user.getPassword())) {
            return false;
        } else {
            user.setPassword(utilityService.getHashedPassword(newPassword, salt));
            return userRepository.save(user).getPassword() != null;
        }
    }

    @Transactional
    public void saveCriticAppliction(Integer id, CriticApplication criticApplication) {
        criticApplication.setUser(utilityService.getUser(id));
        criticApplictionRepository.save(criticApplication);
    }

    public boolean checkUserNameTaken(String userName) {
        return userRepository.findUserByUsername(userName) != null;
    }

    public boolean checkEmailTaken(String email) {
        return userRepository.findUserByEmail(email) != null;
    }

    public UserProfile getProfile(Integer profileId) {
        UserProfile userProfile = getUserProfile(profileId);
//        List<Movie> movies = (List<Movie>) utilityService.shrinkMovieSize(userProfile.getWishList().getMovies());
        List<Movie> movies = userProfile.getWishList().getMovies();
        userProfile.getWishList().setMovies(movies);
        return userProfile;
    }

    public HashMap<Object, Object> getProfileByUsername(String username) {
        UserProfile userProfile = userProfileRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getUserNotFound()));
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        // general: profile image, account status
        if (userProfile.getProfileImage() == null)
            objectObjectHashMap.put("profileImage", "undefined");
        else
            objectObjectHashMap.put("profileImage", userProfile.getProfileImage());
        if (!userProfile.getCritic())
            objectObjectHashMap.put("isCritic", false);
        else
            objectObjectHashMap.put("isCritic", true);
        // if not private, put everything
        if (userProfile.getPrivate() == null || !userProfile.getPrivate()) {
            objectObjectHashMap.put("isPrivate", false);
            objectObjectHashMap.put("biography", userProfile.getBiography());
            objectObjectHashMap.put("isCritic", userProfile.getCritic());
            objectObjectHashMap.put("accountCreatedDate", userProfile.getAccountCreatedDate());
            objectObjectHashMap.put("wishList", userProfile.getWishList().getMovies());
            objectObjectHashMap.put("notInterestedList", userProfile.getNotInterestedList().getMovies());
            objectObjectHashMap.put("userRatings", getUserRatings(userProfile.getId()));
            objectObjectHashMap.put("userReviews", userProfile.getReviews());
            return objectObjectHashMap;
        }
        objectObjectHashMap.put("isPrivate", true);
        return objectObjectHashMap;

    }


    public User getUser(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getProfileNotFound() + id));
    }

    public UserProfile getUserProfile(Integer id) {
        return userProfileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getUserNotFound() + id));
    }

    public List<UserRating> getUserRatings(Integer userProfileId) {
        List<UserRating> userRatings = getUserProfile(userProfileId).getUserRatings();
        userRatings.forEach(userRating -> {
            userRating.getRatedMovie().setCasts(null);
            userRating.getRatedMovie().setAwards(null);
            userRating.getRatedMovie().setPhotos(null);
//            userRating.getRatedTv().setRunTime(null);
            userRating.getRatedMovie().setDirector(null);
        });
        return userRatings;
    }

    @Transactional
    public Boolean setPrivate(Integer userId, Boolean isPrivate) {
        UserProfile userProfile = userProfileRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getProfileNotFound() + userId));
        userProfile.setPrivate(isPrivate);
        userProfileRepository.save(userProfile);
        return isPrivate;
    }

}

