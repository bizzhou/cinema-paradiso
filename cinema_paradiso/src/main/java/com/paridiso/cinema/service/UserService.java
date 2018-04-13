package com.paridiso.cinema.service;

import com.paridiso.cinema.constants.JwtConstants;
import com.paridiso.cinema.entity.Mail;
import com.paridiso.cinema.entity.User;
import com.paridiso.cinema.persistence.UserRepository;
import com.paridiso.cinema.service.implementation.EmailServiceImpl;
import com.paridiso.cinema.service.implementation.UtilityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.Optional;

public abstract class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailServiceImpl emailService;

    @Autowired
    JwtConstants jwtConstants;

    @Autowired
    UtilityServiceImpl utilityService;

    @Transactional
    public Optional<User> login(String email, String password) {
        String hashedPassword = utilityService.getHashedPassword(password, jwtConstants.getSalt());
        return Optional.ofNullable(userRepository.findUserByEmailAndPassword(email, hashedPassword));
    }

    public void forgotPassword(String email) {
        try {
            Mail mail = new Mail(email, "Reset Password - Cinema Paradiso"
                    , "Hello World");
            emailService.sendMessage(mail);
            // TODO: get an email server for sending emails
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
//    User logout(Integer userId);
//
//    boolean changePassword(Integer userId, String oldPassword, String newPassword);
//
//    void forgotPassword(String email);
}
