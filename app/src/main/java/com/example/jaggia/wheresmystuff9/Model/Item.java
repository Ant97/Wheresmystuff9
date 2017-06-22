package com.example.jaggia.wheresmystuff9.Model;

import android.location.Location;
import java.util.Date;

/**
 * Created by jaggia on 6/21/17.
 */

public class Item {

    private enum ItemStatus { RESOLVED, UNRESOLVED};
    private enum ItemType { LOST, FOUND};
    private enum ItemCategory { ELECTRONICS, PERSONAL, ACCESSORY};

    private User user;
    private String name;
    private String description;
    private Date date;
    private Location location;
    private int reward;
    private ItemStatus status;
    private ItemType type;
    private ItemCategory category;

    /**
     * @return current User
     */
    public User getUser() {
        return user;
    }

    /**
     * Set current user to the paramater 'user'
     *
     * @param user
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
     * @param name
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
     * @param description
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
     * @param date
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
     * @param location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Set current Item category to the paramater 'category'
     *
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return current reward for the Item
     */
    public int getReward() {
        return reward;
    }

    /**
     * Set current Item reward to the paramater 'reward'
     *
     * @param reward
     */
    public void setReward(int reward) {
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
     * @param status
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
     * @param type
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
     * Set current Item Category to the paramater 'category'
     *
     * @param category
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
