package com.example.jaggia.wheresmystuff9.Model;

import android.location.Location;
import java.util.Date;

/**
 * Created by jaggia on 6/21/17.
 */

public class Item {

    private enum ItemStatus { LOST, FOUND};

    private User user;
    private String name;
    private String description;
    private Date date;
    private Location location;
    private String category;
    private String type;
    private int reward;
    private ItemStatus status;

    /** ********************************************
     * the getters and setters
     */
    public User getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public Location getLocation() {
        return location;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(ItemStatus status) {
        this.status = status;
    }

    public void setResolved() {
        setStatus(ItemStatus.FOUND);
    }
    public void setUnResolved() {
        setStatus(ItemStatus.LOST);
    }
}
