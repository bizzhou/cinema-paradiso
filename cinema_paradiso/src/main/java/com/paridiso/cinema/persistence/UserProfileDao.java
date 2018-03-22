package com.paridiso.cinema.persistence;

import com.paridiso.cinema.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileDao extends JpaRepository<UserProfile, Integer> {

}
