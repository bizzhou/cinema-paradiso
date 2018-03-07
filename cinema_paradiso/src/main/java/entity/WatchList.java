package entity;

import java.util.LinkedList;

public class WatchList extends LinkedList {

    public static final Integer SIZE_LIMIT = 999;

    private Integer watchListId;
    private Integer userId;

    public static Integer getSizeLimit() {
        return SIZE_LIMIT;
    }

    public Integer getWatchListId() {
        return watchListId;
    }

    public void setWatchListId(Integer watchListId) {
        this.watchListId = watchListId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
