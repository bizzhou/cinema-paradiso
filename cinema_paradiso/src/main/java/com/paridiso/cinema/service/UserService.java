package com.paridiso.cinema.service;

import com.paridiso.cinema.entity.Mail;
import com.paridiso.cinema.entity.User;
import com.paridiso.cinema.persistence.UserDao;
import com.paridiso.cinema.service.implementation.EmailServiceImpl;
import com.paridiso.cinema.service.implementation.UtilityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.Optional;

public abstract class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    EmailServiceImpl emailService;

    @Value("${salt}")
    protected String salt;

    @Autowired
    UtilityServiceImpl utilityService;

    @Transactional
    public Optional<User> login(String email, String password) {
        String hashedPassword = utilityService.getHashedPassword(password, salt);
        return Optional.ofNullable(userDao.findUserByEmailAndPassword(email, hashedPassword));
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
