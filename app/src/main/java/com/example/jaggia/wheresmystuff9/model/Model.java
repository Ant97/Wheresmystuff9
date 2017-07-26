package com.example.jaggia.wheresmystuff9.model;




import com.example.jaggia.wheresmystuff9.model.error_coding.*;
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
     * @return the current user's username
     */
    public static String getCurrentUsername(){
        return _currentUser.getUsername();
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
     * @param email The email the user is associated with
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
        return _database.registerNewUser(user);
    }

    /**
     * Validate a user's username and password
     * @param username username typed by the user
     * @param password password typed by the user
     * @return returns true if user is in the database
     *          returns false if the user is not in the database
     */
    public static boolean validateUser(String username, String password){
        return _database.validateUser(username, password);
    }

    /**
     * Validates that two passwords are the same
     * @param password1 the first password
     * @param password2 the second password
     * @return true if successful, false if not
     */
    private static boolean validatePasswordMatch(String password1, String password2) throws PasswordMismatchException {
         if(PasswordHandler.validatePasswordMatch(password1, password2)) {
             return true;
         } else {
             throw new PasswordMismatchException();
         }

    }

    /**
     * Check that the password meets security requirements
     * @param password string to be tested
     * @return true if the password is acceptable
     * @throws InvalidPasswordException throws if the password is not valid
     */
    private static boolean validatePassword(String password) throws InvalidPasswordException {
        if(PasswordHandler.validatePassword(password)){
            return true;
        } else {
            throw new InvalidPasswordException();
        }
    }

    /**
     * validates password for login
     * @param password password to be validated
     * @return true if valid, false if not
     */
    public static boolean validatePasswordLogin(String password){
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
     * list the items in string format
     *@param itemList the list of items to be listed
     * @return returns a string list of the list of items in itemList
     */
    public static List listItems(ItemList itemList){
        return itemList.listItems();
    }

    /**
     * clears the list of all items
     * @param itemList the list to be cleared
     */
    public static void clearList(ItemList itemList){
        itemList.clearItems();
    }

    /**
     * Find a user by their associated Username
     * @param username the username to be searched for
     * @return the user if found, null if not
     */
    private static User findUserByUsername(String username){
        return UserSearchHandler.findUserByUsername(_database.getUsers(), username);
    }

    /**
     * Find a user by their assocaited Email
     * @param email the email to be searched for
     * @return the user if found, null if not
     */
    public static User findUserByEmail(String email){
        return UserSearchHandler.findUserByEmail(_database.getUsers(), email);
    }

    public static boolean validateEmailFormat(String email) throws InvalidEmailException{
        if(EmailHandler.validateEmailFormat(email)){
            return true;
        } else {
            throw new InvalidEmailException();
        }
    }

    private static boolean validateLegalUsername(String username) throws InvalidUsernameException{
        if(UsernameHandler.validateLegalUsername(username)){
            return true;
        } else {
            throw new InvalidUsernameException();
        }
    }
    private static boolean validatePersonName(String name) throws NoNameException{
        if(UsernameHandler.validatePersonName(name)){
            return true;
        } else {
            throw new InvalidUsernameException();
        }
    }

    /**
     * Validates that the information needed to register a new user is acceptable. Checks username requirements, password requirements, and that the email is of valid format
     * @param name the real life name of the user
     * @param username the publicly associated username
     * @param email the email associated with the account
     * @param pw the password the user desires to use
     * @param pw2 the conformation password
     * @return returns true if the registration information is valid, false if not
     * @throws NoNameException  if no name is given
     * @throws InvalidUsernameException  if the username is not a usable username
     * @throws InvalidPasswordException  if the password does not meet password requirements
     * @throws InvalidEmailException  if the email does not fit the required format
     * @throws PasswordMismatchException  if the passwords do not match
     * @throws DuplicateEmailException if the email is already tied to another account
     * @throws DuplicateUsernameException if the username is already in use by another account
     */
    public static boolean validateLegalRegistration(String name, String username, String email, String pw, String pw2) throws NoNameException, InvalidUsernameException, InvalidPasswordException, InvalidEmailException, PasswordMismatchException, DuplicateEmailException, DuplicateUsernameException {
        if(null != findUserByUsername(username)){
            throw new DuplicateUsernameException();
        }
        if (null != findUserByEmail(email)) {
            throw new DuplicateEmailException();
        }
        return validatePersonName(name) && validateLegalUsername(username) && validateEmailFormat(email) && validatePassword(pw) && validatePasswordMatch(pw, pw2);
    }

}
