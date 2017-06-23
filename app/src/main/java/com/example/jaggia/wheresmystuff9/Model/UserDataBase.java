package com.example.jaggia.wheresmystuff9.Model;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by James on 6/14/2017.
 *
 * Represents a UserDataBase that can contain many Users
 *
 * Temporary class until extra credit database is implemented
 */

public class UserDataBase {

    /**List<> containing all the users for this database*/
    private List<User> _users;

    /**number of Users in database -- not currently used, for future expansion*/
    private int _numUsers;

    /**constructor to initialize the list for the database*/
    public UserDataBase() {
        _users = new ArrayList<>();
        _numUsers = 0;
    }
    /**
     * register a new user in the database using a User object
     * @param nwUser User to be added to the database
     * @return returns ErrorCode.Success if user was added,
     *         returns Nonzero ErrorCode if duplicate username or if user is null
     *         NULLUSER means the user passed in is null
     *
     */
    public ErrorCode registerNewUser(User nwUser) {
        if(null == nwUser){
            return ErrorCode.NULLUSER;
        }
        for (User u : _users) {
            if (u.getUsername().equals(nwUser.getUsername())) {
                return ErrorCode.DUPLICATEUSERNAME;
            }
        }
        _users.add(nwUser);
        _numUsers++;
        return ErrorCode.SUCCESS;
    }

    /**
     * method to validate whether or not a user is in the system
     * @param username the username typed by the user
     * @param password the password typed by the user
     * @return returns true if user is found and has correct password,
     *         returns false if not found or password incorrect, or if null user is passed
     */
    public ErrorCode validateUser(String username, String password) {
        if(null == username){
            return ErrorCode.ILLEGALUSERNAME;
        }
        for (User u : _users) {
            if (u.getUsername().equals(username)) {
                if (u.getPassword().equals(password)) {
                    return ErrorCode.SUCCESS;
                } else {
                    //can be simplified but is left for later development
                    return ErrorCode.INCORRECTPASSWORD;
                }
            }
        }
        return ErrorCode.USERNAMENOTFOUND;
    }

    /**
     * Validates that two passwords are the same
     * @param password1 the first password
     * @param password2 the second password
     * @return ErrorCode SUCCESS if successful, PASSWORDMISMATCH if not
     *
     */
    public ErrorCode validatePassword(String password1, String password2){
        if(password1.equals(password2)){
            return ErrorCode.SUCCESS;
        }
        return ErrorCode.PASSWORDMISMATCH;
    }
}

