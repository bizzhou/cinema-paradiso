package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.entity.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class EmailServiceImpl {

    @Autowired
    private JavaMailSender sender;

    public void sendMessage(Mail mail) throws MessagingException {

        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setTo(mail.getTo());
        mimeMessageHelper.setText(mail.getContent());
        mimeMessageHelper.setSubject(mail.getSubject());

        sender.send(mimeMessage);

    }


}
