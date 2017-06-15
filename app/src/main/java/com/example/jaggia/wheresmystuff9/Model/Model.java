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
    private User _currentUser;

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
    public UserDataBase getDataBase(){
        return _database;
    }
    public User getCurrentUser(){
        return _currentUser;
    }
    public void setCurrentUser(User currentUser){
        _currentUser = currentUser;
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
     * @param user user to be validated
     * @return returns true if user is in the database
     *          returns false if the user is not in the database
     */
    public static boolean validateUser(User user){
        if(_database.validateUser(user) == ErrorCode.SUCCESS) {
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
}
