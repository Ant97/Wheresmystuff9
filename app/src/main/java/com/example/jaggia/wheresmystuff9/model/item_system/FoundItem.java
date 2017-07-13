package com.example.jaggia.wheresmystuff9.model.item_system;

import com.example.jaggia.wheresmystuff9.model.user_system.User;

import java.util.Date;

/**
 * Created by James on 7/11/2017.
 */

public class FoundItem extends Item {

    private String user;
    private String name;
    private String description;
    private Date date;
    private MyLocation location;
    private ItemStatus status;
    private ItemType type;
    private ItemCategory category;

    public FoundItem(){
        user = "default username";
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
    public FoundItem(String user, String name, String description, Date date, MyLocation location, ItemStatus itemStatus, ItemType itemType, ItemCategory itemCategory){
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
        return name + " Found: " + (date.getMonth() + 1) + "/" + date.getDate() + "/" + date.getYear() + " By: " + user;
    }

    public static class Builder{
        private String user;
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
            this.user = "Default Username";
            this.description = "Default description";
            this.date = new Date(1,1,1);
            this.status = ItemStatus.UNRESOLVED;
            this.category = ItemCategory.MISC;
        }
        public Builder User(String u){
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
