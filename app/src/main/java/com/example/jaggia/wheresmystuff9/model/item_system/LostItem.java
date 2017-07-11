package com.example.jaggia.wheresmystuff9.model.item_system;


import com.example.jaggia.wheresmystuff9.model.user_system.User;
//TODO Remove the need to import User

import java.util.Date;

/**
 * Created by jaggia on 6/21/17.
 * edited by James
 *
 * Information holder class for item
 */

public class LostItem implements Item {


    private User user;
    private String name;
    private String description;
    private Date date;
    private MyLocation location;
    private String reward;
    private ItemStatus status;
    private ItemType type;
    private ItemCategory category;

    public LostItem() {
        user = new User();
        name = "default name";
        description = "default description";
        date = new Date();
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
    public LostItem(User user, String name, String description, Date date, MyLocation location, String reward, ItemStatus status, ItemType type, ItemCategory category) {
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

    /**
     * toString method for item
     *
     * @return return the string name
     */
    @Override
    public String toString() {
        return name + " Date: " + (date.getMonth() + 1) + "/" + date.getDate() + "/" + date.getYear() + " By: " + user.getUsername();
    }


    /**
     * @return current User
     */
    @Override
    public User getUser() {
        return user;
    }

    /**
     * Set current user to the paramater 'user'
     *
     * @param user user to be set
     */
    @Override
    public void setUser(User user) {
        this.user = user;
    }


    /**
     * @return current name for the LostItem
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Set current LostItem name to the paramater 'name'
     *
     * @param name name for the item
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return current description for the LostItem
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * Set current LostItem description to the paramater 'description'
     *
     * @param description description for the item
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * @return current date for the LostItem
     */
    @Override
    public Date getDate() {
        return date;
    }

    /**
     * Set current LostItem date to the paramater 'date'
     *
     * @param date date for when the item was lost
     */
    @Override
    public void setDate(Date date) {
        this.date = date;
    }


    /**
     * @return current Location for the LostItem
     */
    @Override
    public MyLocation getLocation() {
        return location;
    }

    /**
     * Set current LostItem location to the paramater 'location'
     *
     * @param location location to set
     */
    @Override
    public void setLocation(MyLocation location) {
        this.location = location;
    }


    /**
     * @return current reward for the LostItem
     */
    public String getReward() {
        return reward;
    }

    /**
     * Set current LostItem reward to the paramater 'reward'
     *
     * @param reward reward for the item
     */
    public void setReward(String reward) {
        this.reward = reward;
    }


    /**
     * @return current status for the LostItem
     */
    @Override
    public ItemStatus getStatus() {
        return status;
    }

    /**
     * Set current LostItem status to the paramater 'status'
     *
     * @param status the status of the item
     */
    @Override
    public void setStatus(ItemStatus status) {
        this.status = status;
    }


    /**
     * @return current type for the LostItem
     */
    @Override
    public ItemType getType() {
        return type;
    }

    /**
     * Set current LostItem type to the paramater 'type'
     *
     * @param type type of item
     */
    @Override
    public void setType(ItemType type) {
        this.type = type;
    }


    /**
     * @return current LostItem Category
     */
    @Override
    public ItemCategory getCategory() {
        return category;
    }

    /**
     * @return ItemCategory values
     */
    public static ItemCategory[] getItemCategoryValues() {
        return ItemCategory.values();
    }

    /**
     * Set current LostItem Category to the paramater 'category'
     *
     * @param category the category of the item
     */
    @Override
    public void setCategory(ItemCategory category) {
        this.category = category;
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
        public LostItem Build(){
            return new LostItem(this);
        }
    }
}
