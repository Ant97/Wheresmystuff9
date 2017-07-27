package com.example.jaggia.wheresmystuff9.model.item_system;


//TODO Remove the need to import User

import java.util.Date;


public class LostItem extends Item {


    private String user;
    private String name;
    private String description;
    private Date date;
    private MyLocation location;
    private String reward;
    private ItemStatus status;
    private ItemType type;
    private ItemCategory category;

    public LostItem() {
        user = "default username";
        name = "default name";
        description = "default description";
        date = new Date(1,1,1);
        location = new MyLocation("default location");
        location.setLatitude(0.0);
        location.setLongitude(0.0);
        reward = "default reward";
        status = ItemStatus.UNRESOLVED;
        type = ItemType.LOST;
        category = ItemCategory.MISC;

    }
    public LostItem(Builder b){
        this.user = b.user;
        this.name = b.name;
        this.description = b.description;
        this.date = b.date;
        this.reward = b.reward;
        this.location = b.location;
        this.status = b.status;
        this.type = b.type;
        this.category = b.category;
    }

    /**
     * Constructor for an item
     *
     * @param user        The user creating the item
     * @param name        The name of the item
     * @param description A description for the item
     * @param date        The date the item was found
     * @param location    The location the item was found
     * @param reward      The reward for finding the item
     * @param status      The current status of the item (resolved or unresolved)
     * @param type        The type of item (lost, found, donated)
     * @param category    The category the item fits into
     */
    public LostItem(String user, String name, String description, Date date, MyLocation location, String reward, ItemStatus status, ItemType type, ItemCategory category) {
        this.user = user;
        this.name = name;
        this.description = description;
        this.date = date;
        this.location = location;
        this.reward = reward;
        this.status = status;
        this.type = type;
        this.category = category;
    }
    public String toString(){
        return name + " Lost ";
    }
    public String getReward(){
        return this.reward;
    }
    public void setReward(String r){
        this.reward = r;
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


    public static class Builder{
        private String user;
        final private String name;
        private String description;
        private Date date;
        private String reward;
        final private MyLocation location;
        private ItemStatus status;
        final private ItemType type;
        private ItemCategory category;

        public Builder(String name, MyLocation location){
            this.name = name;
            this.location = location;
            this.type = ItemType.LOST;
            this.user = "Default username";
            this.description = "Default description";
            this.reward = "Default reward";
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
        public Builder Reward(String r){
            reward = r;
            return this;
        }
        public LostItem Build(){
            return new LostItem(this);
        }
    }
}
