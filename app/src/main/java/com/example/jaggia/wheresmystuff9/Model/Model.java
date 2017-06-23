package com.example.jaggia.wheresmystuff9.Model;

import android.location.Location;

import java.util.Date;

/**
 * Created by James on 6/15/2017.
 *
 * Facade to the model. Singleton Design pattern
 *
 */

public class Model {
    private static final Model _instance = new Model();

    public static Model getInstance() {
        return _instance;
    }

    /** the user database */
    private static UserDataBase _database;
    /**the current user logged in */
    private static User _currentUser;
    /**the current item being operated on */
    private static Item _currentItem;
    /**the List of lost items */
    private static ItemList _lostList = new ItemList();
    /**the List of found items */
    private static ItemList _foundList = new ItemList();
    /**
     * make a new model
     */
    private Model() {
        _database = new UserDataBase();
    }

    /** *****************************************
     * Getters and Setters
     *
     */
    public static UserDataBase getDataBase(){
        return _database;
    }
    public static ItemList getLostList(){ return _lostList; }
    public static ItemList getFoundList() { return _foundList; }

    public static User getCurrentUser(){
        return _currentUser;
    }
    public static void setCurrentUser(User currentUser){
        _currentUser = currentUser;
    }

    public static Item getCurrentItem(){return _currentItem; }
    public static void setCurrentItem(Item currentItem){ _currentItem = currentItem; }

    /**
     *
     * @param name name for the new user
     * @param username username for the new user
     * @param password password for the new user
     * @param userType boolean false means not admin, true means admin
     * @return return the user to the controller
     */
    public static User createNewUser(String name, String username, String password, boolean userType){
        return new User(name, username, password, userType);
    }
    /**
     * Register a new user to the database
     * @param user the user to be registered
     * @return returns true if successful
     *          returns false if unsuccessful
     */
    public static boolean registerNewUser(User user){
        if(_database.registerNewUser(user) == ErrorCode.SUCCESS){
            return true;
        }
        return false;
    }

    /**
     * Validate a user's username and password
     * @param username username typed by the user
     * @param password password typed by the user
     * @return returns true if user is in the database
     *          returns false if the user is not in the database
     */
    public static boolean validateUser(String username, String password){
        if(_database.validateUser(username, password) == ErrorCode.SUCCESS) {
            return true;
        }
        return false;
    }

    /**
     * Validates that two passwords are the same
     * @param password1 the first password
     * @param password2 the second password
     * @return true if successful, false if not
     */
    public static boolean validatePassword(String password1, String password2){
        if(_database.validatePassword(password1, password2) == ErrorCode.SUCCESS){
            return true;
        }
        return false;
    }

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
    public static Item createNewItem(User user, String name, String description, Date date, Location location,
                                     String reward, Item.ItemStatus status, Item.ItemType type, Item.ItemCategory category){
        return new Item(user, name, description, date ,location, reward, status, type, category);
    }
    /**
     * addItem to an item list
     * @param itemList list to have item added to
     * @param item item to be added
     * @return true if successful, false if not
     */
    public static boolean addItem(ItemList itemList, Item item){
        itemList.addItem(item);
        return true;
    }
}
