package com.example.jaggia.wheresmystuff9.model.user_system;

import java.util.List;

public class UserSearchHandler {
    public static User findUserByUsername(List<User> users, String username){
        if (users == null) {
            throw new IllegalArgumentException("user list can't be null");
        }
        if (username == null) {
            throw new IllegalArgumentException("username can't be null");
        }
        for(User u: users){
            if(u.getUsername().equals(username)){
                return u;
            }
        }
        return null;
    }
    public static User findUserByEmail(List<User> users, String email){
        for (User u: users){
            if(u.getEmail().equals(email)){
                return u;
            }
        }
        return null;
    }
}
