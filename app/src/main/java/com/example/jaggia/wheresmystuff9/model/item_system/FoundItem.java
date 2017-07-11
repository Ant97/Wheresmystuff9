package com.example.jaggia.wheresmystuff9.model.item_system;

import com.example.jaggia.wheresmystuff9.model.user_system.User;

import java.util.Date;

/**
 * Created by James on 7/11/2017.
 */

public class FoundItem implements Item {

    private User user;
    private String name;
    private String description;
    private Date date;
    private MyLocation location;
    private ItemStatus status;
    private ItemType type;
    private ItemCategory category;

    public FoundItem(){
        user = new User();
        name = "default name";
        description = "default description";
        date = new Date();
        location = new MyLocation("default location");
        location.setLatitude(0.0);
        location.setLongitude(0.0);
        status = ItemStatus.UNRESOLVED;
        type = ItemType.FOUND;
        category = ItemCategory.MISC;
    }
    public FoundItem(Builder b){
        this.user = b.user;
        this.name = b.name;
        this.description = b.description;
        this.date = b.date;
        this.location = b.location;
        this.status = b.status;
        this.type = b.type;
        this.category = b.category;
    }
    public FoundItem(User user, String name, String description, Date date, MyLocation location, ItemStatus itemStatus, ItemType itemType, ItemCategory itemCategory){
        this.user = user;
        this.name = name;
        this.description = description;
        this.date = date;
        this.location = location;
        this.status = itemStatus;
        this.type = itemType;
        this.category = itemCategory;
    }
    @Override
    public String toString() {
        return name + " Date: " + (date.getMonth() + 1) + "/" + date.getDate() + "/" + date.getYear() + " By: " + user.getUsername();
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User u) {
        this.user = u;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public MyLocation getLocation() {
        return location;
    }

    @Override
    public void setLocation(MyLocation location) {
        this.location = location;
    }

    @Override
    public ItemStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(ItemStatus itemStatus) {
        this.status = itemStatus;
    }

    @Override
    public ItemType getType() {
        return type;
    }

    @Override
    public void setType(ItemType itemType) {
        this.type = itemType;
    }

    @Override
    public ItemCategory getCategory() {
        return category;
    }

    @Override
    public void setCategory(ItemCategory itemCategory) {
        this.category = itemCategory;
    }

    public static class Builder{
        private User user;
        final private String name;
        private String description;
        private Date date;
        final private MyLocation location;
        private ItemStatus status;
        final private ItemType type;
        private ItemCategory category;

        public Builder(String name, MyLocation location){
            this.name = name;
            this.location = location;
            this.type = ItemType.FOUND;
            User tempuser = new User();
            tempuser.setName("Default item User");
            this.user = tempuser;
            this.description = "Default description";
            this.date = new Date();
            this.status = ItemStatus.UNRESOLVED;
            this.category = ItemCategory.MISC;
        }
        public Builder User(User u){
            user = u;
            return this;
        }
        public Builder Description(String d){
            description = d;
            return this;
        }
        public Builder Date(Date d){
            date = d;
            return this;
        }
        public Builder ItemStatus(ItemStatus s){
            status = s;
            return this;
        }
        public Builder ItemCategory(ItemCategory c){
            category = c;
            return this;
        }
        public FoundItem Build(){
            return new FoundItem(this);
        }
    }
}
