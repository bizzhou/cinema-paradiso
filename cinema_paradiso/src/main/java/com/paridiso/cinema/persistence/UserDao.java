package com.paridiso.cinema.persistence;

import com.paridiso.cinema.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User, Integer> {

    User findUserByEmailAndPassword(String email, String password);

}
