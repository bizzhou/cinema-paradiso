package com.paridiso.cinema.entity;

import javax.persistence.*;
import java.util.LinkedList;

@Entity
@Table(name = "WishList", uniqueConstraints = @UniqueConstraint(columnNames = "wishlistId"))
public class WishList extends LinkedList {

    public static final Integer SIZE_LIMIT = 999;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer wishlistId;

    public WishList() {
    }

    public static Integer getSizeLimit() {
        return SIZE_LIMIT;
    }

    public Integer getWatchListId() {
        return wishlistId;
    }

    public void setWatchListId(Integer wishlistId) {
        this.wishlistId = wishlistId;
    }

}
