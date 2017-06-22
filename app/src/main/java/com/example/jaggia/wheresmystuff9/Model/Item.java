package com.example.jaggia.wheresmystuff9.Model;

import android.location.Location;
import java.util.Date;

/**
 * Created by jaggia on 6/21/17.
 */

public class Item {

    private enum ItemStatus { RESOLVED, UNRESOLVED};
    private enum ItemType { LOST, FOUND};

    private User user;
    private String name;
    private String description;
    private Date date;
    private Location location;
    private String category;
    private int reward;
    private ItemStatus status;
    private ItemType type;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }



    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public void setResolved() {
        setStatus(ItemStatus.RESOLVED);
    }
    public void setUnResolved() {
        setStatus(ItemStatus.UNRESOLVED);
    }
}
