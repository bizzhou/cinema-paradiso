package com.paridiso.cinema.service;

import java.util.List;

public interface ListService {

    Integer getSize();

    void addToList(Integer userId, String filmId);

    List<?> getList();

    boolean removeFromList(Long filmId);

}
