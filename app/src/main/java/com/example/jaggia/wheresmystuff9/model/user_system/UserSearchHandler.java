package com.example.jaggia.wheresmystuff9.model.user_system;

import java.util.List;

/**
 * Created by James on 7/11/2017.
 */

public class UserSearchHandler {
    public User findUserByUsername(List<User> users, String username){
        for(User u: users){
            if(u.getUsername().equals(username)){
                return u;
            }
        }
        return null;
    }
    public User findUserByEmail(List<User> users, String email){
        for (User u: users){
            if(u.getEmail().equals(email)){
                return u;
            }
        }
        return null;
    }
}
