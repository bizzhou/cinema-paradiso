package com.paridiso.cinema.persistence;

import com.paridiso.cinema.entity.NotInterestedList;
import com.paridiso.cinema.entity.WatchList;
import com.paridiso.cinema.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotInterestedListRepository  extends JpaRepository<NotInterestedList, Integer> {
}
