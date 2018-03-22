package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.entity.User;
import com.paridiso.cinema.entity.UserProfile;
import com.paridiso.cinema.entity.enumerations.Role;
import com.paridiso.cinema.exceptions.UserAlreadyExistException;
import com.paridiso.cinema.persistence.UserProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import com.paridiso.cinema.persistence.UserDao;
import com.paridiso.cinema.service.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class RegUserServiceImpl extends UserService {

    @Autowired
    UtilityServiceImpl utilityService;

    @Autowired
    UserDao userDao;

    @Autowired
    UserProfileDao userProfileDao;

    @Transactional
    public Optional<User> signup(User user) {

        user.setRole(Role.ROLE_USER);
        user.setAccountSuspended(false);
        user.setPassword(utilityService.getHashedPassword(user.getPassword(), salt));

        try {

            // UserProfile p = userProfileDao.save(new UserProfile());
            // user.setUserProfile(p);

            return Optional.ofNullable(userDao.save(user));

        } catch (Exception e) {

            System.out.println(e.getMessage());
            throw new UserAlreadyExistException("EMAIL DUPLICATE/INVALID EMAIL: ",
                    user.getUsername(), user.getEmail());
        }

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
