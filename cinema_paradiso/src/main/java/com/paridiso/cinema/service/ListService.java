package com.paridiso.cinema.service;

import java.util.List;

public interface ListService {

    Integer getSize();

    boolean addToList(Long filmId);

    List<?> getList();

    boolean removeFromList(Long filmId);

}
