package com.paridiso.cinema.service.implementation;

import com.paridiso.cinema.service.UtilityService;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.nio.charset.StandardCharsets.*;

@Service
public class UtilityServiceImpl implements UtilityService {

    @Override
    public String getHashedPassword(String passwordToHash, String salt) {

        String hashedPassword = null;

        try {

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(salt.getBytes(UTF_8));
            byte[] bytes = messageDigest.digest(passwordToHash.getBytes(UTF_8));
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < bytes.length; i++) {
                stringBuilder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }


            hashedPassword = stringBuilder.toString();

        } catch (NoSuchAlgorithmException e) {
            System.out.println("PASSWORD HASHING ALGORITHM DOESN'T EXIST");
        }

        return hashedPassword;
    }

}

