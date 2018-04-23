package com.paridiso.cinema.service;

import com.paridiso.cinema.entity.Mail;
import com.paridiso.cinema.entity.User;
import com.paridiso.cinema.persistence.UserRepository;
import com.paridiso.cinema.service.implementation.EmailServiceImpl;
import com.paridiso.cinema.service.implementation.UtilityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.Optional;

public abstract class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailServiceImpl emailService;

    @Value("${jwt.salt}")
    protected String salt;

    @Value("${application.exception.userNotFound")
    protected String userNotFind;

    @Autowired
    UtilityServiceImpl utilityService;

    @Transactional
    public Optional<User> login(String email, String password) {
        String hashedPassword = utilityService.getHashedPassword(password, salt);
        User user = userRepository.findUserByEmailAndPassword(email, hashedPassword);
        if (user.getAccountSuspended()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, userNotFind);
        }
        return Optional.of(user);
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

}
