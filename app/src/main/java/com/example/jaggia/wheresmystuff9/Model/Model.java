package com.example.jaggia.wheresmystuff9.Model;

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

    private static Item _currentItem;

    private static ItemList _lostList = new ItemList();

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
    public static boolean validatePassword(String password1, String password2){
        if( password1.equals(password2)){
            return true;
        }
        return false;
    }

    public static boolean addItem(Item item){
        _lostList.addItem(item);
        return true;
    }
}
