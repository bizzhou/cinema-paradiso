package com.paridiso.cinema.persistence;

import com.paridiso.cinema.entity.CriticApplication;
import com.paridiso.cinema.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CriticApplictionRepository extends JpaRepository<CriticApplication, Integer> {
    Optional<CriticApplication> findByUser(User user);
}
