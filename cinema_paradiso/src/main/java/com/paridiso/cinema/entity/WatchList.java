package com.paridiso.cinema.entity;

import javax.persistence.*;
import java.util.LinkedList;

@Entity
@Table(name = "WatchList")
public class WatchList extends LinkedList {

    public static final Integer SIZE_LIMIT = 999;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer watchListId;

    public static Integer getSizeLimit() {
        return SIZE_LIMIT;
    }

    public Integer getWatchListId() {
        return watchListId;
    }

    public void setWatchListId(Integer watchListId) {
        this.watchListId = watchListId;
    }

}
