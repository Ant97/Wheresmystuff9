package com.example.jaggia.wheresmystuff9.model;



import com.example.jaggia.wheresmystuff9.model.error_coding.ErrorCode;
import com.example.jaggia.wheresmystuff9.model.item_system.*;
import com.example.jaggia.wheresmystuff9.model.user_system.*;


import java.util.List;

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
    private static UserDatabase _database;
    /**the current user logged in */
    private static User _currentUser;
    /**the current item being operated on */
    private static Item _currentItem;
    /**the List of lost items */
    private static final ItemList _lostList = new ItemList();
    /**the List of found items */
    private static final ItemList _foundList = new ItemList();
    /**
     * make a new model
     */
    private Model() {
        _database = new UserDatabase();
    }

    /* *****************************************
     * Getters and Setters
     *
     */
    /**
     *
     * @return  the user database
     */
    public static UserDatabase getDataBase(){
        return _database;
    }

    /**
     *
     * @return the lostList
     */
    public static ItemList getLostList(){ return _lostList; }

    /**
     *
     * @return the found list
     */
    public static ItemList getFoundList() { return _foundList; }

    /**
     *
     * @return the current user
     */
    public static User getCurrentUser(){
        return _currentUser;
    }

    /**
     *
     * @param currentUser set the current user
     */
    public static void setCurrentUser(User currentUser){
        _currentUser = currentUser;
    }

    /**
     *
     * @return get the current item
     */
    public static Item getCurrentItem(){return _currentItem; }

    /**
     *
     * @param currentItemObject set the current item
     */
    public static void setCurrentItem(Item currentItemObject){ _currentItem = currentItemObject; }



    /**
     *
     * @param name name for the new user
     * @param username username for the new user
     * @param password password for the new user
     * @param userType boolean false means not admin, true means admin
     * @return return the user to the controller
     */
    public static User createNewUser(String name, String username, String password, boolean userType, String email){
        return new User(name, username, password, userType, email);
    }
    /**
     * Register a new user to the database
     * @param user the user to be registered
     * @return returns true if successful
     *          returns false if unsuccessful
     */
    public static boolean registerNewUser(User user){
        return _database.registerNewUser(user) == ErrorCode.SUCCESS;
    }

    /**
     * Validate a user's username and password
     * @param username username typed by the user
     * @param password password typed by the user
     * @return returns true if user is in the database
     *          returns false if the user is not in the database
     */
    public static boolean validateUser(String username, String password){
        return _database.validateUser(username, password) == ErrorCode.SUCCESS;
    }

    /**
     * Validates that two passwords are the same
     * @param password1 the first password
     * @param password2 the second password
     * @return true if successful, false if not
     */
    public static boolean validatePasswordMatch(String password1, String password2){
        return PasswordHandler.validatePasswordMatch(password1, password2);

    }

    public static boolean validatePassword(String password){
        return PasswordHandler.validatePassword(password);
    }
    /**
     * addItem to an itemObject list
     * @param itemList list to have itemObject added to
     * @param item itemObject to be added
     * @return true if successful, false if not
     */
    public static boolean addItem(ItemList itemList, Item item){
        if (item != null) {
            itemList.addItem(item);
            return true;
        } else {
            return false;
        }
    }
    /**
     *@param itemList the list of items to be listed
     * @return returns a string list of the list of items in itemList
     */
    public static List listItems(ItemList itemList){
        return itemList.listItems();
    }

    public static void clearList(ItemList itemList){
        itemList.clearItems();
    }

    public static User findUserByUsername(String username){
        return UserSearchHandler.findUserByUsername(_database.getUsers(), username);
    }
    public static User findUserByEmail(String email){
        return UserSearchHandler.findUserByEmail(_database.getUsers(), email);
    }

    public static boolean validateEmailFormat(String email){
        return EmailHandler.validateEmailFormat(email);
    }
    public static String getCurrentUsername(){
        return _currentUser.getUsername();
    }

    public static boolean validateLegalUsername(String username){
        return UsernameHandler.validateLegalUsername(username);
    }
    public static boolean validatePersonName(String name){
        return UsernameHandler.validatePersonName(name);
    }
    public static boolean validateLegalRegistration(String name, String username, String email, String pw, String pw2){
        return validatePersonName(name) && validateLegalUsername(username) && validateEmailFormat(email) && validatePassword(pw) && validatePasswordMatch(pw, pw2) && (null == findUserByUsername(username)) && (null == findUserByEmail(email));
    }

}
