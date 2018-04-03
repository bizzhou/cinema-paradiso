package com.paridiso.cinema.persistence;

import com.paridiso.cinema.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByEmailAndPassword(String email, String password);

    User findUserByEmail(String email);
}
