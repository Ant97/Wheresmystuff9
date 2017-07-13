package com.example.jaggia.wheresmystuff9.model.user_system;

/**
 * Created by James on 7/11/2017.
 */

public class UsernameHandler {
    private static final int VALIDUSERNAMELENGTH = 4;
    private static final int VALIDPERSONNAMELENGTH = 1;
    public static boolean validateLegalUsername(String username){
        if(null == username){
            return false;
        }
        return !(username.length() < VALIDUSERNAMELENGTH);
    }
    public static boolean validatePersonName(String name){
        if(null == name){
            return false;
        }
        return !(name.length() < VALIDPERSONNAMELENGTH);
    }
}
