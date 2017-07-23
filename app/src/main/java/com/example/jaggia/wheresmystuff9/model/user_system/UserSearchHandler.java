package com.example.jaggia.wheresmystuff9.model.user_system;

import java.util.List;

/**
 * This is a user search handler class the handles user search either by username or email
 */
public class UserSearchHandler {
    /**
     * This method find and return User object based on the user list and username passed in
     * --**no user cannot have duplicate username**--
     * @throws IllegalArgumentException if user list passed in is null
     * @param users a list of users to search through
     * @param username the username to search for
     * @return a user with the unique username
     */
    public static User findUserByUsername(List<User> users, String username){
        if (users == null) {
            throw new IllegalArgumentException("user list can't be null");
        }
        if (username == null || !UsernameHandler.validateLegalUsername(username)) {
            return null;
        }
        for(User u: users){
            if(u.getUsername().equals(username)){
                return u;
            }
        }
        return null;
    }

    /**
     * This method find and return User object based on the user list and email passed in
     * --**no user cannot have duplicate email**--
     * @throws IllegalArgumentException if user list passed in is null
     * @param users a list of users to search through
     * @param email the email to search for
     * @return a user with the unique email
     */
    public static User findUserByEmail(List<User> users, String email){
        if (users == null) {
            throw new IllegalArgumentException("user list can't be null");
        }
        if (email == null || !EmailHandler.validateEmailFormat(email)) {
            return null;
        }
        for (User u: users){
            if(u.getEmail().equals(email)){
                return u;
            }
        }
        return null;
    }
}
