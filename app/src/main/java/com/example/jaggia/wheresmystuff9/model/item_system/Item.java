package com.example.jaggia.wheresmystuff9.model.item_system;

import com.example.jaggia.wheresmystuff9.model.user_system.User;

import java.util.Date;

/**
 * Created by James on 7/11/2017.
 */

public abstract class Item {

    private String user;
    private String name;
    private String description;
    private Date date;
    private MyLocation location;
    private ItemStatus status;
    private ItemType type;
    private ItemCategory category;

    public String toString(){
        return name + " Date: " + (date.getMonth() + 1) + "/" + date.getDate() + "/" + date.getYear() + " By: " + user;
    }


    public String getUsername() {
        return user;
    }


    public void setUsername(String u) {
        this.user = u;
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


    public MyLocation getLocation() {
        return location;
    }


    public void setLocation(MyLocation location) {
        this.location = location;
    }


    public ItemStatus getStatus() {
        return status;
    }


    public void setStatus(ItemStatus itemStatus) {
        this.status = itemStatus;
    }


    public ItemType getType() {
        return type;
    }


    public void setType(ItemType itemType) {
        this.type = itemType;
    }


    public ItemCategory getCategory() {
        return category;
    }


    public void setCategory(ItemCategory itemCategory) {
        this.category = itemCategory;
    }
}

