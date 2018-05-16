package com.paridiso.cinema.persistence;

import com.paridiso.cinema.entity.NotInterestedList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotInterestedListRepository  extends JpaRepository<NotInterestedList, Integer> {
}
