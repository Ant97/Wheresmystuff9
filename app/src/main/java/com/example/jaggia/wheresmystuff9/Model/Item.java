package com.example.jaggia.wheresmystuff9.Model;

import android.location.Location;
import java.util.Date;

/**
 * Created by jaggia on 6/21/17.
 * edited by James
 *
 * Information holder class for item
 */

public class Item {

    public enum ItemStatus { RESOLVED, UNRESOLVED}
    public enum ItemType { LOST, FOUND}
    public enum ItemCategory { ELECTRONICS, PERSONAL, ACCESSORY, MISC}

    private User user;
    private String name;
    private String description;
    private Date date;
    private Location location;
    private String reward;
    private ItemStatus status;
    private ItemType type;
    private ItemCategory category;

    /**
     * Constructor for an item
     * @param user The user creating the item
     * @param name The name of the item
     * @param description A description for the item
     * @param date The date the item was found
     * @param location The location the item was found
     * @param reward The reward for finding the item
     * @param status The current status of the item (resolved or unresolved)
     * @param type The type of item (lost, found, donated)
     * @param category The category the item fits into
     */
    public Item(User user, String name, String description, Date date, Location location, String reward, ItemStatus status, ItemType type, ItemCategory category){
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
     * @return return the string name
     */
    public String toString(){
        return name + "    Posted: " +  date.getMonth() + "/" + date.getDate() + "/" + date.getYear() + " By: " + user.getName();
//        return name;
    }


    /**
     * @return current User
     */
    public User getUser() {
        return user;
    }

    /**
     * Set current user to the paramater 'user'
     *
     * @param user user to be set
     */
    public void setUser(User user) {
        this.user = user;
    }



    /**
     * @return current name for the Item
     */
    public String getName() {
        return name;
    }

    /**
     * Set current Item name to the paramater 'name'
     *
     * @param name name for the item
     */
    public void setName(String name) {
        this.name = name;
    }



    /**
     * @return current description for the Item
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set current Item description to the paramater 'description'
     *
     * @param description description for the item
     */
    public void setDescription(String description) {
        this.description = description;
    }



    /**
     * @return current date for the Item
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set current Item date to the paramater 'date'
     *
     * @param date date for when the item was lost
     */
    public void setDate(Date date) {
        this.date = date;
    }



    /**
     * @return current Location for the Item
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Set current Item location to the paramater 'location'
     *
     * @param location location to set
     */
    public void setLocation(Location location) {
        this.location = location;
    }



    /**
     * @return current reward for the Item
     */
    public String getReward() {
        return reward;
    }

    /**
     * Set current Item reward to the paramater 'reward'
     *
     * @param reward reward for the item
     */
    public void setReward(String reward) {
        this.reward = reward;
    }




    /**
     * @return current status for the Item
     */
    public ItemStatus getStatus() {
        return status;
    }

    /**
     * Set current Item status to the paramater 'status'
     *
     * @param status the status of the item
     */
    public void setStatus(ItemStatus status) {
        this.status = status;
    }




    /**
     * @return current type for the Item
     */
    public ItemType getType() {
        return type;
    }

    /**
     * Set current Item type to the paramater 'type'
     *
     * @param type type of item
     */
    public void setType(ItemType type) {
        this.type = type;
    }




    /**
     * @return current Item Category
     */
    public ItemCategory getCategory() {
        return category;
    }

    /**
     *
     * @return ItemCategory values
     */
    public  static ItemCategory[] getItemCategoryValues(){
        return ItemCategory.values();
    }

    /**
     * Set current Item Category to the paramater 'category'
     *
     * @param category the category of the item
     */
    public void setCategory(ItemCategory category) {
        this.category = category;
    }





    /**
     * Set current Item resolved status to RESOLVED
     */
    public void setResolved() {
        setStatus(ItemStatus.RESOLVED);
    }

    /**
     * Set current Item resolved status to UNRESOLVED
     */
    public void setUnResolved() {
        setStatus(ItemStatus.UNRESOLVED);
    }
}
