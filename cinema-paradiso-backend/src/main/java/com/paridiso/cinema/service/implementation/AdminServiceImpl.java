package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.constants.ExceptionConstants;
import com.paridiso.cinema.entity.CriticApplication;
import com.paridiso.cinema.entity.User;
import com.paridiso.cinema.entity.UserProfile;
import com.paridiso.cinema.entity.enumerations.Role;
import com.paridiso.cinema.persistence.CriticApplictionRepository;
import com.paridiso.cinema.persistence.UserProfileRepository;
import com.paridiso.cinema.persistence.UserRepository;
import com.paridiso.cinema.service.UserService;
import com.paridiso.cinema.service.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @Autowired
    ExceptionConstants exceptionConstants;

    @Autowired
    CriticApplictionRepository criticApplicationRepository;

    @Autowired
    UtilityService utilityService;


    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll()
                .stream()
                .filter(user -> user.getRole().equals(Role.ROLE_CRITIC) || user.getRole().equals(Role.ROLE_USER)
                        && !user.getAccountSuspended())
                .collect(Collectors.toList());
        users.forEach(user -> user.setPassword(null));
        return users;
    }

    public Boolean suspendUser(Integer id) {
        User user = getUser(id);
        user.setAccountSuspended(true);
        return userRepository.save(user).getUserID() != null;
    }

    private User getUser(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getUserNotFound()));
    }

    @Transactional
    public void makeCritic(Integer userID) {
        User user = utilityService.getUser(userID);
        user.getUserProfile().setCritic(true);
        user.setRole(Role.ROLE_CRITIC);
        CriticApplication criticApplication = criticApplicationRepository
                .findByUser(user).orElseThrow(() -> new ResponseStatusException(INTERNAL_SERVER_ERROR, exceptionConstants.getAppNotFound()));
        criticApplication.setApproved(true);
        user.getUserProfile().getReviews().forEach(review -> {
            review.setCriticReview(true);
        });
        userRepository.save(user);
        userProfileRepository.save(user.getUserProfile());
        criticApplicationRepository.save(criticApplication);
    }

    public List<CriticApplication> getAllCriticApplication() {
        return criticApplicationRepository.findAll()
                .stream()
                .filter(criticApplication -> criticApplication.isApproved() == false)
                .collect(Collectors.toList());
    }

    public String saveFile(MultipartFile file, String movie) {
        if (!file.isEmpty()) {
            byte[] bytes = new byte[0];
            try {
                bytes = file.getBytes();
                File dir = new File("images/" + movie);
                if (!dir.exists()) dir.mkdirs();
                String newFile = "images/" + movie + "/" + file.getOriginalFilename();
                Path path = Paths.get(newFile);
                Files.write(path, bytes);
                return newFile;
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        } else {
            return "";
        }
    }
}
