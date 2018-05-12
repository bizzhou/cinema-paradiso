package com.paridiso.cinema.persistence;

import com.paridiso.cinema.entity.User;
import com.paridiso.cinema.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUsername(String userName);
    User findUserByEmail(String email);
    User findUserByUserProfile(UserProfile userProfile);
    User findUserByEmailAndPassword(String email, String password);
}
