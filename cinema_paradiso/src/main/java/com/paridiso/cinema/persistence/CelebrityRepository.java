package com.paridiso.cinema.persistence;

import com.paridiso.cinema.entity.Celebrity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CelebrityRepository extends JpaRepository<Celebrity, String>{
    Optional<Celebrity> findCelebrityByCelebrityId(String celebrityId);
}
