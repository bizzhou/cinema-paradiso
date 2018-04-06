package com.paridiso.cinema.persistence;

import com.paridiso.cinema.entity.Celebrity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CelebrityRepository extends JpaRepository<Celebrity, String>{
    Celebrity findCelebrityByCelebrityId(String celebrityId);
}
